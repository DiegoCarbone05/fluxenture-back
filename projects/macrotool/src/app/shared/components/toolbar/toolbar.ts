import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'flux-toolbar',
  standalone: false,
  templateUrl: './toolbar.html',
  styleUrl: './toolbar.scss'
})
export class Toolbar {

  @Output() menuClick = new EventEmitter<void>();
  @Input() title: string = '';

  clickMenu() {
    this.menuClick.emit();
  }

}
