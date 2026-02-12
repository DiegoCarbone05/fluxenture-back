import { app, IpcMainEvent } from 'electron';
import { FsService } from '../services/fs.service';
import { EventService } from './event.service';
import { FileService } from '../services/file.service';
import fs from 'fs';
import path from 'path';



export const eventSVC: EventService = EventService.getInstance();
export const fsSVC: FsService = FsService.getInstance();
export const fileSVC: FileService = FileService.getInstance();

eventSVC.listen('LOAD_PDF', (event: IpcMainEvent, payload: any) => {
  fileSVC.loadPDF().then((result) => {
    event.returnValue = result;
  });
});

eventSVC.listen('SAVE_PDF', async (event: IpcMainEvent, payload: any) => {
  try {
    const { arrayBuffer, cd } = payload;
    
    if (!arrayBuffer || !cd || !cd.cdPath) {
      event.returnValue = { success: false, error: 'Missing required parameters' };
      return;
    }

    // Merge PDFs using the FileService method
    const mergedPdfBytes = await fileSVC.mergePDFs(cd.cdPath, arrayBuffer);
    
    // Show save dialog with default filename
    const defaultFilename = `${cd.cdName}_${cd.emissionDate}_${cd.tntResultData?.status}_(${cd.obs}).pdf`;
    const saveResult = await fileSVC.saveFileDialog(defaultFilename);
    
    if (saveResult.canceled) {
      event.returnValue = { success: false, canceled: true };
      return;
    }
    
    // Write the merged PDF to the selected path
    const outputPath = saveResult.filePath || path.join(app.getPath('documents'), defaultFilename);
    await fsSVC.writeFileBuffer(outputPath, mergedPdfBytes);
    
    event.returnValue = { success: true, path: outputPath };
    
  } catch (error) {
    console.error('Error merging PDFs:', error);
    event.returnValue = { success: false, error: error instanceof Error ? error.message : 'Unknown error' };
  }
});
