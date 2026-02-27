import { FsService } from '../services/fs.service';
import { EventService } from './event.service';
import { FileService } from '../services/file.service';



export const eventSVC: EventService = EventService.getInstance();
export const fsSVC: FsService = FsService.getInstance();
export const fileSVC: FileService = FileService.getInstance();

