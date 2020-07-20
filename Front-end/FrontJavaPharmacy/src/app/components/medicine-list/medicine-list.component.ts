import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medicine } from '../../models/medicine.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  medicines: Medicine[];
  displayedColumns: string[] = [
    'medicineId',
    'name',
    'monthDuration',
    'generic',
    'minimumPrice'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Medicine[]>('http://localhost:8082/medicines')
    .subscribe(medicines => this.medicines = medicines);
  }

}
