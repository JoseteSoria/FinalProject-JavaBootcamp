import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { Order } from '../../models/order/order.model';
import { MedicineOrdered } from '../../models/order/medicineOrdered.model';
import { MedicinesToStoreDTO } from '../../models/order/medicinesToStoreDTO.model'

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {


  orderForm: FormGroup;
  order: Order;
  isShow: boolean;
  medicineId: number;
  quantity: number;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa('owner:owner')
    }),
  };

  constructor(private router: Router,
              private fb: FormBuilder,
              private http: HttpClient,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.orderForm = this.fb.group({
      medicinesToStoreDTO: this.fb.array([])
    });
  }

  get medicinesToStoreDTOForms() {
    return this.orderForm.get('medicinesToStoreDTO') as FormArray;
  }

  addMedicineToStoreDTO() {
    const medicineToStoreDTO = this.fb.group({
      medicineId: ['', [Validators.required, Validators.min(1)]],
      quantity: ['', [Validators.required, Validators.min(1)]]
    });
    this.medicinesToStoreDTOForms.push(medicineToStoreDTO);
    console.log(this.orderForm.value);
    console.log(this.medicinesToStoreDTOForms.value);
  }

  deleteMedicineToStoreDTO(i) {
    this.medicinesToStoreDTOForms.removeAt(i);
  }

  submitForm(): void {
    const medicinesToOrder = this.medicinesToStoreDTOForms.value;
    this.http.post<any>('http://localhost:8080/orders/place-order', medicinesToOrder, this.httpOptions).subscribe(
      (data) => {
        this.router.navigate(['/orders']);
      }
    );
  }

}
