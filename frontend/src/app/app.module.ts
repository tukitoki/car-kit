import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DetailAddComponent } from './detail-add/detail-add.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RequestComponent } from './request/request.component';
import { MileageDetailsComponent } from './request/mileage-details/mileage-details.component';
import { DetailComponent } from './detail/detail.component';
import { TimetableComponent } from './timetable/timetable.component';
import { CarAddComponent } from './car-add/car-add.component';
import { MaintenanceWorkAddComponent } from './maintenance-work-add/maintenance-work-add.component';
import { CarListComponent } from './car-list/car-list.component';
import { PaginationComponent } from './pagination/pagination.component';
import { MileageDetailsAddComponent } from './mileage-details-add/mileage-details-add.component';
import { CarAddDetailComponent } from './car-add-detail/car-add-detail.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { MainComponent } from './main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    DetailAddComponent,
    DetailComponent,
    RequestComponent,
    MileageDetailsComponent,
    TimetableComponent,
    CarAddComponent,
    MaintenanceWorkAddComponent,
    CarListComponent,
    PaginationComponent,
    MileageDetailsAddComponent,
    CarAddDetailComponent,
    LoginComponent,
    HeaderComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
