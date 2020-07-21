import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Sales } from '../../models/sell/sales.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-sales-list',
  templateUrl: './sales-list.component.html',
  styleUrls: ['./sales-list.component.css']
})
export class SalesListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

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
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      this.router.navigate(['/login']);
    }
    else{
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };
      this.http.get<Sales[]>('http://localhost:8080/sales', this.httpOptions)
      .subscribe(sales => this.sales = sales);
    }
  }

}
