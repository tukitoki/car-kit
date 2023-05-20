import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailAddComponent } from './detail-add/detail-add.component';
import { RequestComponent } from './request/request.component';
import { DetailComponent } from './detail/detail.component';
import { CarAddComponent } from './car-add/car-add.component';
import { MaintenanceWorkAddComponent } from './maintenance-work-add/maintenance-work-add.component';
import { CarListComponent } from './car-list/car-list.component';
import { MileageDetailsAddComponent } from './mileage-details-add/mileage-details-add.component';
import { CarAddDetailComponent } from './car-add-detail/car-add-detail.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { RequestListComponent } from './request-list/request-list.component';

const routes: Routes = [
  { path: 'detail/add', component: DetailAddComponent },
  { path: 'request', component: RequestComponent },
  { path: 'detail/:id', component: DetailComponent },
  { path: 'car', component: CarAddComponent },
  { path: 'work/car', component: MaintenanceWorkAddComponent },
  { path: 'cars', component: CarListComponent },
  { path: 'car/mileage-details', component: MileageDetailsAddComponent },
  { path: 'car/add-details', component: CarAddDetailComponent },
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainComponent },
  { path: 'requests', component: RequestListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
