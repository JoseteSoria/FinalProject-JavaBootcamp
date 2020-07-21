import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';

import { Sales } from '../../models/sell/sales.model';
import { MedicineSold } from '../../models/sell/medicineSold.model';
import { MedicinesToSellDTO } from '../../models/sell/medicinesToSellDTO.model';
import { mixinDisableRipple } from '@angular/material/core';

@Component({
  selector: 'app-sales-form',
  templateUrl: './sales-form.component.html',
  styleUrls: ['./sales-form.component.css']
})
export class SalesFormComponent implements OnInit {

  salesForm: FormGroup;
  medicineToSales: MedicinesToSellDTO[];
  isShow: boolean;
  j: number;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa('owner:owner')
    }),
  };

  constructor(private router: Router,
              private fb: FormBuilder,
              private http: HttpClient) { }

  ngOnInit(): void {
    this.salesForm = this.fb.group({
      userId: '',
      patientId: '',
      medicinesToSellDTO: this.fb.array([])
    });
    this.medicineToSales = [];
    this.j = 0;
  }

  get userId() {
    return this.salesForm.get('userId');
  }

  get patientId() {
    return this.salesForm.get('patientId');
  }

  get medicinesToSellDTOForms() {
    return this.salesForm.get('medicinesToSellDTO') as FormArray;
  }

  addMedicineToSellDTO() {
    const medicineToSellDTO = this.fb.group({
      warehouseMedicineId: ['', [Validators.required, Validators.min(1)]],
    });
    this.medicinesToSellDTOForms.push(medicineToSellDTO);
  }

  deleteMedicineToSellDTO(i) {
    this.medicinesToSellDTOForms.removeAt(i);
    // this.medicineToSales.splice(this.j - 1, 1);
  }

  submitForm(): void {
    const medicinesToSale = this.medicinesToSellDTOForms.value;
    for (let i = 0; i < this.medicinesToSellDTOForms.value.length; i++) {
      this.medicineToSales
      .push({ warehouseMedicineId: this.medicinesToSellDTOForms.at(i).value.warehouseMedicineId,
        userId: this.userId.value, patientId: this.patientId.value});
    }
    console.log('medicineToSales' + this.medicineToSales);
    console.log('medicineToSales.values' + this.medicineToSales.values);
    this.http.post<any>('http://localhost:8080/sales/make-sale', this.medicineToSales, this.httpOptions).subscribe(
      (data) => {
        this.router.navigate(['/sales']);
      }
    );
  }

}
