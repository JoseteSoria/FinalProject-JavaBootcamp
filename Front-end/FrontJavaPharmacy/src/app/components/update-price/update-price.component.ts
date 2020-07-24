import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { User } from '../../models/user/user.model';

@Component({
  selector: 'app-update-price',
  templateUrl: './update-price.component.html',
  styleUrls: ['./update-price.component.css']
})
export class UpdatePriceComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  newPriceForm: FormGroup;
  warehouseMedicineId: number;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user == null){
      this.router.navigate(['/login']);
    } else if (this.user.role !== 'ROLE_OWNER') {
      this.router.navigate(['/']);
    } else {
      this.httpOptions = {
        headers: new HttpHeaders({
          'Content-Type':  'application/json',
          Authorization: 'Basic ' + btoa( this.user.username + ':' + this.user.password)
        })
      };

      this.newPriceForm = this.formBuilder.group({
        newPrice: ['', [Validators.required, Validators.pattern((/^\d+\.\d{1,2}$/))]],
      });

      this.route.params.subscribe((params) => {
        this.warehouseMedicineId = params.id;
      });
    }
  }

  submitForm(): void {
    this.http.put('http://localhost:8080/warehouse-medicines/' + this.warehouseMedicineId
    + '/update-price/' + this.newPriceForm.value.newPrice, null, this.httpOptions).subscribe(
      (data) => {
        this.router.navigate(['/warehouse-medicines']);
      },
      (error) => {
        this.snackBar.open('Something went wrong!', 'X', {
          duration: 2000,
        });
      }
    );
  }

}
