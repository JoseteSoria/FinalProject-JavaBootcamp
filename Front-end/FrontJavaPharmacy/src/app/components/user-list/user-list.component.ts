import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/user.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];
  displayedColumns: string[] = [
    'userId',
    'name',
    'username',
    'role'
  ];


  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<User[]>('http://localhost:8081/users')
    .subscribe(users => this.users = users);
  }

}
