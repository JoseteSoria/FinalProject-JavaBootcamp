import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from '../../models/order/order.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[];
  displayedColumns: string[] = [
    'orderId',
    'date',
    'totalPrice'
  ];

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<Order[]>('http://localhost:8084/orders')
    .subscribe(orders => this.orders = orders);
  }

}
