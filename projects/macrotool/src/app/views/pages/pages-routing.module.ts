import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Tnt } from './tnt/tnt';
import { CdsViewer } from './tnt/cds-viewer/cds-viewer';
import { Eployees } from './eployees/eployees';

const routes: Routes = [
  {
    path: 'tnt',
    component: Tnt,
    children: [
      {
        path: 'cds-viewer/:id',
        component: CdsViewer
      }
    ]
  },
  {
    path: 'eployees',
    component: Eployees
  },
  {
    path: 'cds-viewer/:id',
    component: CdsViewer
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PagesRoutingModule {}
