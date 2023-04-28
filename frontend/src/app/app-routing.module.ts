import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailComponent } from './detail-add/detail-add.component';
import { RequestComponent } from './request/request.component';

const routes: Routes = [
  { path: 'api/detail/add', component: DetailComponent },
  { path: 'api/request', component: RequestComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
