import { Component, computed, effect, EventEmitter, Input, Output } from '@angular/core';
import { AuthService } from '../../../core/services/auth/auth.service';


@Component({
  selector: 'flux-toolbar',
  standalone: false,
  templateUrl: './toolbar.html',
  styleUrl: './toolbar.scss'
})
export class Toolbar {

  @Output() menuClick = new EventEmitter<void>();
  @Input() menuButton: boolean = false;
  @Input() title: string = '';
  @Output() backClick = new EventEmitter<void>();
  @Input() backButton: boolean = false;

  user = computed(() => this.authService.getUserSignal()());

  constructor(private authService: AuthService) {
  }

  logout() {
    this.authService.logout();
  }

  clickBack() {
    this.backClick.emit();
  }
  clickMenu() {
    this.menuClick.emit();
  }

}
