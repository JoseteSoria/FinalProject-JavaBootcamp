import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sales } from '../../models/sell/sales.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-sales-list',
  templateUrl: './sales-list.component.html',
  styleUrls: ['./sales-list.component.css']
})
export class SalesListComponent implements OnInit {

  sales: Sales[];
  displayedColumns: string[] = [
    'salesId',
    'userId',
    'patientId',
    'date',
    'totalPrice'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Sales[]>('http://localhost:8085/sales')
    .subscribe(sales => this.sales = sales);
  }

}
