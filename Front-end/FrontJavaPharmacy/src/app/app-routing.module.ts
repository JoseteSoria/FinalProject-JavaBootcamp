import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { WarehouseMedicineListComponent } from './components/warehouse-medicine-list/warehouse-medicine-list.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { MedicineOrderedListComponent } from './components/medicine-ordered-list/medicine-ordered-list.component';

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'medicines', component: MedicineListComponent },
  { path: 'warehouse-medicines', component: WarehouseMedicineListComponent },
  { path: 'patients', component: PatientListComponent},
  { path: 'orders', component: OrderListComponent},
  { path: 'medicines-ordered', component: MedicineOrderedListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
