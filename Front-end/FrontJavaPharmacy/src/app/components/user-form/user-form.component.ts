import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
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
  userForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(4)]],
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(4)]],
      role: ['', [Validators.required, Validators.minLength(4)]],
    });
  }

  submitForm(): void {
    this.http.post<User>('http://localhost:8081/users', this.userForm.value).subscribe(
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
