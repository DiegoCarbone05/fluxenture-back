import { contextBridge, ipcRenderer } from 'electron';

// Expose protected methods that allow the renderer process to use
// the ipcRenderer without exposing the entire object
contextBridge.exposeInMainWorld('electronAPI', {
  ipcRenderer: {
    // Send messages to main process
    send: (channel: string, ...args: any[]) => {
      ipcRenderer.send(channel, ...args);
    },
    // Send synchronous messages to main process
    sendSync: (channel: string, ...args: any[]) => {
      return ipcRenderer.sendSync(channel, ...args);
    },
    // Listen for messages from main process
    on: (channel: string, func: (...args: any[]) => void) => {
      // Strip event (first parameter) as it includes `sender` which is a security risk
      ipcRenderer.on(channel, (event, ...args) => func(...args));
    },
    // Remove listener
    removeListener: (channel: string, func: (...args: any[]) => void) => {
      ipcRenderer.removeListener(channel, func);
    },
    // Remove all listeners
    removeAllListeners: (channel: string) => {
      ipcRenderer.removeAllListeners(channel);
    }
  }
});

