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

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'medicines', component: MedicineListComponent },
  { path: 'warehouse-medicines', component: WarehouseMedicineListComponent },
  { path: 'patients', component: PatientListComponent},
  { path: 'orders', component: OrderListComponent},
  { path: 'medicines-ordered', component: MedicineOrderedListComponent},
  { path: 'sales', component: SalesListComponent},
  { path: 'medicines-sold', component: MedicineSoldListComponent},
  { path: 'users/create', component: UserFormComponent },
  { path: 'orders/create', component: OrderFormComponent },
  { path: 'sales/create', component: SalesFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
