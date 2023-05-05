import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailAddComponent } from './detail-add/detail-add.component';
import { RequestComponent } from './request/request.component';
import { DetailComponent } from './detail/detail.component';

const routes: Routes = [
  { path: 'api/detail/add', component: DetailAddComponent },
  { path: 'api/request', component: RequestComponent },
  { path: 'api/detail/:id', component: DetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
