import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { Router } from '@angular/router';

import { User } from '../../models/user/user.model';

import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  user: User;
  loginError: string;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    if (this.user != null) {
      this.router.navigate(['/']);
    } else {
      this.loginForm = this.formBuilder.group({
        username: ['', [Validators.required, Validators.minLength(4)]],
        password: ['', [Validators.required, Validators.minLength(4)]],
      });
    }
  }

  login(): void {
    console.log(this.loginForm.value);
    this.http
      .post<string>(
        'http://localhost:8080/users/login',
        this.loginForm.value
      )
      .subscribe(
        (data) => {
          this.user = {id: null, name: null, username: this.loginForm.value.username, password: this.loginForm.value.password, role: data };

          localStorage.setItem('currentUser', JSON.stringify(this.user));
          this.router.navigate(['/']);
        },
        (error) => {
          this.snackBar.open('Something went wrong!', 'X', {
            duration: 2000,
          });
          this.loginError = error;
        }
      );
  }

}
