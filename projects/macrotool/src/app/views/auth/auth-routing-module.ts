import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Login } from './login/login';
import { Splash } from './splash/splash';
import { authGuard } from '../../core/guards/auth.guard';

const routes: Routes = [
  {
    path: 'login',
    component: Login
  },
  {
    path: '',
    component: Splash,
    canActivate: [authGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
