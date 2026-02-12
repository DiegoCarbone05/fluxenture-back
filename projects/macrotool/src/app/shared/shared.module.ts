import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { Sidenav } from './components/sidenav/sidenav';
import { Toolbar } from './components/toolbar/toolbar';
import { StatusChip } from './status-chip/status-chip';
import { Titlebar } from './components/titlebar/titlebar';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSortModule } from '@angular/material/sort';
import { MatMenuModule } from '@angular/material/menu';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { TntStatusPipePipe } from './pipes/tnt-status.pipe-pipe';

@NgModule({
  declarations: [
    Sidenav,
    Toolbar,
    StatusChip,
    Titlebar,
    TntStatusPipePipe
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSortModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatMenuModule,
    MatAutocompleteModule,
  ],
  exports: [
    Sidenav,
    Toolbar,
    StatusChip,
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    Titlebar,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    MatSortModule,
    MatProgressSpinnerModule,
    FormsModule,
    MatMenuModule,
    MatAutocompleteModule,
    TntStatusPipePipe

  ]
})
export class SharedModule { }
