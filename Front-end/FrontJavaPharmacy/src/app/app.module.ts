import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserListComponent } from './components/user-list/user-list.component';
import { WarehouseMedicineListComponent } from './components/warehouse-medicine-list/warehouse-medicine-list.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { MedicineOrderedListComponent } from './components/medicine-ordered-list/medicine-ordered-list.component';
import { SalesListComponent } from './components/sales-list/sales-list.component';
import { MedicineSoldListComponent } from './components/medicine-sold-list/medicine-sold-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { OrderFormComponent } from './components/order-form/order-form.component';
import { SalesFormComponent } from './components/sales-form/sales-form.component';
import { LoginComponent } from './components/login/login.component';
import { IndexComponent } from './components/index/index.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';
import { UpdatePriceComponent } from './components/update-price/update-price.component';
import { ExpiryListComponent } from './components/expiry-list/expiry-list.component';


@NgModule({
  declarations: [
    AppComponent,
    MedicineListComponent,
    UserListComponent,
    WarehouseMedicineListComponent,
    PatientListComponent,
    OrderListComponent,
    MedicineOrderedListComponent,
    SalesListComponent,
    MedicineSoldListComponent,
    NavbarComponent,
    UserFormComponent,
    OrderFormComponent,
    SalesFormComponent,
    LoginComponent,
    IndexComponent,
    PatientFormComponent,
    UpdatePriceComponent,
    ExpiryListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
