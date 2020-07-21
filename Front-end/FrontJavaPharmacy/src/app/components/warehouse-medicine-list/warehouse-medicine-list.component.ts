import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WarehouseMedicine } from '../../models/medicine/warehouseMedicine.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-warehouse-medicine-list',
  templateUrl: './warehouse-medicine-list.component.html',
  styleUrls: ['./warehouse-medicine-list.component.css']
})
export class WarehouseMedicineListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

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
      this.http.get<WarehouseMedicine[]>('http://localhost:8080/warehouse-medicines', this.httpOptions)
      .subscribe(warehouseMedicines => this.warehouseMedicines = warehouseMedicines);
    }
  }

}
