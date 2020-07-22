import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineSold } from '../../models/sell/medicineSold.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-medicine-sold-list',
  templateUrl: './medicine-sold-list.component.html',
  styleUrls: ['./medicine-sold-list.component.css']
})
export class MedicineSoldListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

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
      this.http.get<MedicineSold[]>('http://localhost:8080/medicines-sold', this.httpOptions)
      .subscribe(medicinesSold => this.medicinesSold = medicinesSold);
    }


  }

}
