import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineOrdered } from '../../models/order/medicineOrdered.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-medicine-ordered-list',
  templateUrl: './medicine-ordered-list.component.html',
  styleUrls: ['./medicine-ordered-list.component.css']
})
export class MedicineOrderedListComponent implements OnInit {

  medicinesOrdered: MedicineOrdered[];
  displayedColumns: string[] = [
    'medicineOrderedId',
    'medicineId',
    'medicineName',
    'quantity',
    'orderId'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<MedicineOrdered[]>('http://localhost:8084/medicines-ordered')
    .subscribe(medicinesOrdered => this.medicinesOrdered = medicinesOrdered);
  }

}
