import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicineOrdered } from '../../models/order/medicineOrdered.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';
@Component({
  selector: 'app-medicine-ordered-list',
  templateUrl: './medicine-ordered-list.component.html',
  styleUrls: ['./medicine-ordered-list.component.css']
})
export class MedicineOrderedListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

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
      this.http.get<MedicineOrdered[]>('http://localhost:8080/medicines-ordered', this.httpOptions)
      .subscribe(medicinesOrdered => this.medicinesOrdered = medicinesOrdered);
    }
  }

}
