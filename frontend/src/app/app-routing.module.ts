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

const routes: Routes = [
  { path: 'api/detail/add', component: DetailAddComponent },
  { path: 'api/request', component: RequestComponent },
  { path: 'api/detail/:id', component: DetailComponent },
  { path: 'api/car', component: CarAddComponent },
  { path: 'api/work/car', component: MaintenanceWorkAddComponent },
  { path: 'api/cars', component: CarListComponent },
  { path: 'api/car/mileage-details', component: MileageDetailsAddComponent },
  { path: 'api/car/add-details', component: CarAddDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
