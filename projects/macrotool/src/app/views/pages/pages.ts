import { Component, computed } from '@angular/core';
import { AuthService } from '../../core/services/auth/auth.service';



@Component({
  selector: 'app-pages',
  standalone: false,
  templateUrl: './pages.html',
  styleUrl: './pages.scss'
})
export class Pages {

  constructor(
    private authService: AuthService
  ) {
    this.authService.saveUserInSignal();
  }



}
