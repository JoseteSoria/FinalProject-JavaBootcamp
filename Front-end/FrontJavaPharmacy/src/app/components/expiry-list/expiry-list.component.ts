import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { WarehouseMedicine } from '../../models/medicine/warehouseMedicine.model';
import { User } from '../../models/user/user.model';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-expiry-list',
  templateUrl: './expiry-list.component.html',
  styleUrls: ['./expiry-list.component.css']
})
export class ExpiryListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  monthForm: FormGroup;

  warehouseMedicines: WarehouseMedicine[];
  displayedColumns: string[] = [
    'warehouseMedicineId',
    'name',
    'generic',
    'price',
    'expirationDate',
    'delete'
  ];
  dataSource = new MatTableDataSource<WarehouseMedicine>(this.warehouseMedicines);

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      this.router.navigate(['/login']);
    } else {
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };

      this.monthForm = this.formBuilder.group({
        month: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      });
    }
  }

  submitForm(): void {
    console.log(this.monthForm.value.month);

    console.log('http://localhost:8080/warehouse-medicines/near-expiration/'
    + this.monthForm.value.month);

    this.http.get<WarehouseMedicine[]>('http://localhost:8080/warehouse-medicines/near-expiration/'
    + this.monthForm.value.month, this.httpOptions)
    .subscribe(warehouseMedicines =>
      {this.warehouseMedicines = warehouseMedicines;
       this.dataSource = new MatTableDataSource<WarehouseMedicine>(this.warehouseMedicines); });
    console.log(this.warehouseMedicines);
    this.router.navigate(['/warehouse-medicines/check-expiry']);
  }

  deleteMedicine(med: WarehouseMedicine, i: number): void {
    console.log(med.id);
    console.log(i);
    console.log(this.warehouseMedicines);
    this.warehouseMedicines.splice(i, 1);
    this.dataSource = new MatTableDataSource<WarehouseMedicine>(this.warehouseMedicines);
    this.dataSource._updateChangeSubscription();
    this.http.delete('http://localhost:8080/warehouse-medicines/delete/' + med.id, this.httpOptions).subscribe(
      (data) => {
      },
      (error) => {
        this.snackBar.open('Something went wrong!', 'X', {
          duration: 2000,
        });
      }
    );
  }

}
