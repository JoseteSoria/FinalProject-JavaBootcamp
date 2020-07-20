import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MedicineListComponent } from './components/medicine-list/medicine-list.component';


const routes: Routes = [
  { path: 'medicines', component: MedicineListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
