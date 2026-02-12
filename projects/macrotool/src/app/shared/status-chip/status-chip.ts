import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-status-chip',
  standalone: false,
  templateUrl: './status-chip.html',
  styleUrl: './status-chip.scss'
})
export class StatusChip {
  @Input() status: any = 'N/A';
}
