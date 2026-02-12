import { Component, ElementRef, signal, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cd } from '../../../../shared/models/Cd.model';
import { TrackAndTrace } from '../../../../shared/services/track-and-trace';
import html2Canvas from 'html2canvas';
import { jsPDF } from 'jspdf';
import { Tnt } from '../../../../shared/models/Tnt.model';
import { CdService } from '../../../../core/services/cd-api/cd.service';
import { Employee } from '../../../../shared/models/Employee';
import { EmployeeService } from '../../../../core/services/employees/employee.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cds-viewer',
  standalone: false,
  templateUrl: './cds-viewer.html',
  styleUrl: './cds-viewer.scss',
})
export class CdsViewer {

  @ViewChild('imprZone', { static: false }) imprZone!: ElementRef;

  cd = signal<Cd | null>(null);
  employee = signal<Employee | null>(null)

  displayedColumns: string[] = ['date', 'plant', "historyData", 'status',];
  dataSource = new MatTableDataSource<Tnt>([]);

  async generarPdfDesdeHtml() {
    const data = this.imprZone.nativeElement;

    // 2. Usar html2canvas para convertir el HTML en una imagen (canvas)
    const canvas = await html2Canvas(data);
    const imgData = canvas.toDataURL('image/png');

    // 3. Crear el documento PDF usando jsPDF
    const pdf = new jsPDF('p', 'mm', 'a4'); // 'p' = portrait, 'mm' = unidades, 'a4' = formato
    const imgWidth = 208; // Ancho A4 en mm (menos márgenes)
    const pageHeight = 295; // Alto A4 en mm
    const imgHeight = (canvas.height * imgWidth) / canvas.width;
    let heightLeft = imgHeight;

    let position = 10;

    // Lógica para manejar páginas múltiples si el contenido es muy largo
    pdf.addImage(imgData, 'PNG', 1, position, imgWidth, imgHeight);
    heightLeft -= pageHeight;

    while (heightLeft >= 0) {
      position = heightLeft - imgHeight;
      pdf.addPage();
      pdf.addImage(imgData, 'PNG', 1, position, imgWidth, imgHeight);
      heightLeft -= pageHeight;
    }

    // 4. Obtener el PDF como Blob o ArrayBuffer para enviarlo al servidor
    const nuevoPdfBlob = pdf.output('blob');
    console.log('PDF de HTML generado como Blob:', nuevoPdfBlob);

    return nuevoPdfBlob;
  }

  constructor(
    private route: ActivatedRoute,
    private trackAndTrace: TrackAndTrace,
    private router: Router,
    private cdService: CdService,
    private employeeSvc: EmployeeService
  ) {
    this.route.params.subscribe((params) => {

      //Obtiene el Numero de seguimiento desde el params url
      const trackingNumber = params['id'];
      //Obtiene la CD del numero de seguimiento
      const cd = this.cdService.getLocalCdByTrackingNumber(Number(trackingNumber))

      //Si el cdNumber existe y existe la CD, se cargan a los signals
      if (trackingNumber && cd) {
        this.cd.set(cd)

        //Se obtiene el empleado asociado
        const employee = this.employeeSvc.getLocalEmployeeById(cd?.employeeId)
        //Si existe, se carga
        if (employee) this.employee.set(employee)

      }

      this.trackAndTrace.trackPackage(trackingNumber as string).then((res) => {
        const tnt: Tnt[] = res as unknown as Tnt[];
        this.loadTracking(tnt)
      })
    });
  }

  ngOnInit() {
  }

  exportPDF() {

  }

  loadTracking(tnt: Tnt[]) {
    const currentCd = this.cd()!;
    if (currentCd.tnt !== tnt) {
      currentCd.tnt = tnt
      this.cdService.putCd(currentCd).subscribe()
      this.cd.set(currentCd)
      this.dataSource.data = currentCd.tnt
    }
    console.log(this.cd());
    

  }

  copyDeliveryDate() {

    // const date = this.trackAndTrace.getLastTntResult(this.trackAndTraceResult() || '').date;
    // navigator.clipboard.writeText(date.split(' ')[0]);
    alert('Fecha de entrega copiada al portapapeles');
  }

  goBack() {
    this.router.navigate(['/main', 'tnt']);
  }


}
