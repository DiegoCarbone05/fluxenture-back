import { Component, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-prompt',
  standalone: false,
  templateUrl: './prompt.html',
  styleUrl: './prompt.scss'
})
export class Prompt {
  readonly dialogRef = inject(MatDialogRef<Prompt>);
  readonly data = inject(MAT_DIALOG_DATA);

  closeDialog() {
    this.dialogRef.close();
  }
}
