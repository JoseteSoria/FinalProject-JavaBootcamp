import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medicine } from '../../models/medicine/medicine.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

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
      this.http.get<Medicine[]>('http://localhost:8080/medicines', this.httpOptions)
      .subscribe(medicines => this.medicines = medicines);
    }
  }

}
