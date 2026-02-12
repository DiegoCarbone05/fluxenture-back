import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

const routes: Routes = [
  {
    path: 'main',
    loadChildren: () => import('./views/pages/pages.module').then(m => m.PagesModule),
    canActivate: [authGuard]
  },
  {
    path: '',
    loadChildren: () => import('./views/auth/auth-module').then(m => m.AuthModule),
  },
  {
    path: '**',
    redirectTo: 'splash'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
