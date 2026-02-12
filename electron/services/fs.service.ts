import fs from 'fs';

export class FsService {
  private static instance: FsService;

  public static getInstance(): FsService {
    if(!this.instance) this.instance = new FsService();
    return this.instance;
  }

  readFile(path: string): Promise<string> {
    return fs.promises.readFile(path, 'utf8');
  }

  readFileAsBuffer(path: string): Promise<Buffer> {
    return fs.promises.readFile(path);
  }

  writeFile(path: string, data: string): Promise<void> {
    return fs.promises.writeFile(path, data);
  }

  writeFileBuffer(path: string, data: Buffer | Uint8Array): Promise<void> {
    return fs.promises.writeFile(path, data);
  }
}