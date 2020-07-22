import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { WarehouseMedicineListComponent } from './components/warehouse-medicine-list/warehouse-medicine-list.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { MedicineOrderedListComponent } from './components/medicine-ordered-list/medicine-ordered-list.component';
import { SalesListComponent } from './components/sales-list/sales-list.component';
import { MedicineSoldListComponent } from './components/medicine-sold-list/medicine-sold-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { OrderFormComponent } from './components/order-form/order-form.component';
import { SalesFormComponent } from './components/sales-form/sales-form.component';
import { LoginComponent } from './components/login/login.component';
import { IndexComponent } from './components/index/index.component';
import { PatientFormComponent } from './components/patient-form/patient-form.component';
import { UpdatePriceComponent } from './components/update-price/update-price.component';
import { ExpiryListComponent } from './components/expiry-list/expiry-list.component';

const routes: Routes = [
  { path: '', component: IndexComponent},
  { path: 'login', component: LoginComponent},
  { path: 'users', component: UserListComponent },
  { path: 'medicines', component: MedicineListComponent },
  { path: 'warehouse-medicines', component: WarehouseMedicineListComponent },
  { path: 'patients', component: PatientListComponent},
  { path: 'orders', component: OrderListComponent},
  { path: 'medicines-ordered', component: MedicineOrderedListComponent},
  { path: 'sales', component: SalesListComponent},
  { path: 'medicines-sold', component: MedicineSoldListComponent},
  { path: 'users/create', component: UserFormComponent },
  { path: 'patients/create', component: PatientFormComponent },
  { path: 'orders/create', component: OrderFormComponent },
  { path: 'sales/create', component: SalesFormComponent },
  { path: 'warehouse-medicines/check-expiry', component: ExpiryListComponent },
  { path: 'warehouse-medicines/:id/update-price', component: UpdatePriceComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
