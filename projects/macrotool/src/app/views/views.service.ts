import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ViewsService {

  private titlebarColor = signal<string>('#e6e6e6');
  private titlebarTextColor = signal<string>('#000000');
  private titlebarFloating = signal<boolean>(false);
  private titlebarFullscreen = signal<boolean>(false);

  public setTitlebarFloating(value: boolean) {
    this.titlebarFloating.set(value);
  }

  public setTitlebarColor(value: string) {
    this.titlebarColor.set(value);
  }

  public setTitlebarTextColor(value: string) {
    this.titlebarTextColor.set(value);
  }

  public setTitlebarFullscreen(value: boolean) {
    this.titlebarFullscreen.set(value);
  }

  public getTitlebarColor() {
    return this.titlebarColor();
  }

  public getTitlebarTextColor() {
    return this.titlebarTextColor();
  }

  public getTitlebarFullscreen() {
    return this.titlebarFullscreen();
  }

  public getTitlebarFloating() {
    return this.titlebarFloating();
  }

}
