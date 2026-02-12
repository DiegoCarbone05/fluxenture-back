import { Component, signal, OnInit } from '@angular/core';
import { Electron } from './shared/services/electron';
import { AuthService } from './core/services/auth/auth.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.scss'
})
export class App{

  constructor(private electron: Electron,  private authService: AuthService, private router: Router ) {

  }

}
