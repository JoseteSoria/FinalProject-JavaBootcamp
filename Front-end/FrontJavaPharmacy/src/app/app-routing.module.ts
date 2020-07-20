import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { WarehouseMedicineListComponent } from './components/warehouse-medicine-list/warehouse-medicine-list.component';


const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'medicines', component: MedicineListComponent },
  { path: 'warehouse-medicines', component: WarehouseMedicineListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
