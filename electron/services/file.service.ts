import { FsService } from './fs.service';

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
  

}