import { app, dialog, OpenDialogReturnValue, SaveDialogReturnValue } from "electron";
import { PDFDocument } from 'pdf-lib';
import { FsService } from './fs.service';
import path from 'path';

export class FileService {
  private static instance: FileService;
  private fsService: FsService;

  public static getInstance(): FileService {
    if(!this.instance) this.instance = new FileService();
    return this.instance;
  }

  constructor() {
    this.fsService = FsService.getInstance();
  }
  

  loadPDF(): Promise<OpenDialogReturnValue> {
    return dialog.showOpenDialog({
      properties: ['openFile'],
      filters: [{ name: 'PDF', extensions: ['pdf'] }],
      title: 'Load PDF',
      defaultPath: app.getPath('documents')
    });
  }

  openFolderDialog(): Promise<OpenDialogReturnValue> {
    return dialog.showOpenDialog({
      properties: ['openDirectory']
    });
  }

  saveFileDialog(defaultFilename?: string): Promise<SaveDialogReturnValue> {
    return dialog.showSaveDialog({
      properties: ['createDirectory', 'showOverwriteConfirmation'],
      filters: [{ name: 'PDF', extensions: ['pdf'] }],
      defaultPath: defaultFilename ? path.join(app.getPath('documents'), defaultFilename) : undefined
    });
  }

  /**
   * Merge two PDFs: one from file path and one from ArrayBuffer
   * @param existingPdfPath Path to the existing PDF file
   * @param newPdfBuffer ArrayBuffer of the new PDF to merge
   * @returns Promise with the merged PDF as Uint8Array
   */
  async mergePDFs(existingPdfPath: string, newPdfBuffer: ArrayBuffer): Promise<Uint8Array> {
    // Read the existing PDF file
    const existingPdfBytes = await this.fsService.readFileAsBuffer(existingPdfPath);
    
    // Load both PDFs
    const existingPdf = await PDFDocument.load(existingPdfBytes);
    const newPdf = await PDFDocument.load(newPdfBuffer);
    
    // Create a new PDF document
    const mergedPdf = await PDFDocument.create();
    
    // Copy all pages from the existing PDF
    const existingPages = await mergedPdf.copyPages(existingPdf, existingPdf.getPageIndices());
    existingPages.forEach((page) => {
      mergedPdf.addPage(page);
    });
    
    // Copy all pages from the new PDF
    const newPages = await mergedPdf.copyPages(newPdf, newPdf.getPageIndices());
    newPages.forEach((page) => {
      mergedPdf.addPage(page);
    });
    
    // Save and return the merged PDF
    return await mergedPdf.save();
  }
}