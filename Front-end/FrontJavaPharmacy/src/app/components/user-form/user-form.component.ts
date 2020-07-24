import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { User } from '../../models/user/user.model';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router,
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
    }

    this.userForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      role: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  submitForm(): void {
    this.http.post<User>('http://localhost:8080/users', this.userForm.value, this.httpOptions).subscribe(
      (data) => {
        this.router.navigate(['/users']);
      },
      (error) => {
        this.snackBar.open('Something went wrong!', 'X', {
          duration: 2000,
        });
      }
    );
  }

}
