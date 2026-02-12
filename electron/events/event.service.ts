import { ipcMain } from 'electron';
import { IpcMainEvent } from 'electron';
import { BrowserWindow } from 'electron/main';

export class EventService {
  private static instance: EventService;

  public static getInstance(): EventService {
    if (!this.instance) this.instance = new EventService();
    return this.instance;
  }

  /**
   * Escucha eventos generales (BROADCAST)
   * @param event
   * @param callback
   */
  listen(event: string, callback: (event: IpcMainEvent, payload: any) => void) {
    ipcMain.on(event, callback);
  }

  emit(event: string, value: any, windows:BrowserWindow) {
    windows.webContents.send(event, value);
  }
}
