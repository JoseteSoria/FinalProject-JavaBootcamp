import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { Patient } from '../../models/patient/patient.model';

import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.css']
})
export class PatientFormComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  patientForm: FormGroup;

  constructor(private router: Router,
              private fb: FormBuilder,
              private http: HttpClient,
              private snackBar: MatSnackBar) { }

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

      this.patientForm = this.fb.group({
        name: ['', [Validators.required, Validators.minLength(4)]],
        birthday: ['', [Validators.required, Validators.pattern(/^(18|19|20)\d\d([- /.])(0[1-9]|1[012])\2(0[1-9]|[12][0-9]|3[01])$/)]],
        phoneNumber: ['', [Validators.required, Validators.pattern(/^[0-9]{9}$/)]]
      });
    }
  }

  submitForm(): void {
    this.http.post<User>('http://localhost:8080/patients', this.patientForm.value, this.httpOptions).subscribe(
      (data) => {
        this.router.navigate(['/patients']);
      },
      (error) => {
        this.snackBar.open('Something went wrong!', 'X', {
          duration: 2000,
        });
      }
    );
  }
}
