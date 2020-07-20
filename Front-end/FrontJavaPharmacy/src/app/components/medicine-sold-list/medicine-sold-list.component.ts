import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineSold } from '../../models/sell/medicineSold.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-medicine-sold-list',
  templateUrl: './medicine-sold-list.component.html',
  styleUrls: ['./medicine-sold-list.component.css']
})
export class MedicineSoldListComponent implements OnInit {

  medicinesSold: MedicineSold[];
  displayedColumns: string[] = [
    'medicineSoldId',
    'medicineId',
    'medicineName',
    'price',
    'salesId'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<MedicineSold[]>('http://localhost:8085/medicines-sold')
    .subscribe(medicinesSold => this.medicinesSold = medicinesSold);
  }

}
