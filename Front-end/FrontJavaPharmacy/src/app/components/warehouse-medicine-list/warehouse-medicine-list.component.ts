import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WarehouseMedicine } from '../../models/warehouseMedicine.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-warehouse-medicine-list',
  templateUrl: './warehouse-medicine-list.component.html',
  styleUrls: ['./warehouse-medicine-list.component.css']
})
export class WarehouseMedicineListComponent implements OnInit {

  warehouseMedicines: WarehouseMedicine[];
  displayedColumns: string[] = [
    'warehouseMedicineId',
    'name',
    'generic',
    'price',
    'expirationDate'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<WarehouseMedicine[]>('http://localhost:8082/warehouse-medicines')
    .subscribe(warehouseMedicines => this.warehouseMedicines = warehouseMedicines);
  }

}
