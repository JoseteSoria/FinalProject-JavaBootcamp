import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../../models/order/order.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user/user.model';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  user: User;
  httpOptions = {headers: new HttpHeaders({'Content-Type':  'application/json'})};

  orders: Order[];
  displayedColumns: string[] = [
    'orderId',
    'date',
    'totalPrice'
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
      this.http.get<Order[]>('http://localhost:8080/orders', this.httpOptions)
      .subscribe(orders => this.orders = orders);
    }
  }

}
