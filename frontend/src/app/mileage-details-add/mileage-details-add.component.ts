import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../service/car.service';
import { MaintenanceWorkService } from '../service/maintenance-work.service';
import { MaintenanceAddResponse } from '../entity/MaintenanceAddResponse';
import { DetailMileageAdd } from '../entity/detail/DetailMileageAdd';
import { AuthService } from '../service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mileage-details-add',
  templateUrl: './mileage-details-add.component.html',
  styleUrls: ['./mileage-details-add.component.css']
})
export class MileageDetailsAddComponent implements OnInit {

  detailsMileages!: DetailMileageAdd[];
  addResponse!: MaintenanceAddResponse;
  carId!: number;
  indexMileage: number = 0

  constructor(
    private carService: CarService,
    private authService: AuthService,
    private maintenanceService: MaintenanceWorkService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
  }

  ngOnInit(): void {
    if (this.authService.username.getValue() == null) {
      Swal.fire("Login to see this page").then(() => this.router.navigate(['/login']));
      return;
    } else if (!this.authService.checkAuthoritiy('MILEAGE_ADD')) {
      Swal.fire("U can't access this page").then(() => this.router.navigate(['/main']))
      return;
    }
    this.maintenanceService.showAddCarWork().subscribe({
      next: value => this.addResponse = value
    })
    this.activatedRt.queryParams.subscribe(params => {
      this.carId = params['id']
    })
    this.detailsMileages = []
  }

  addMileageDetail() {
    this.detailsMileages.push({ detailType: this.addResponse.detailTypes[0], mileage: 0 })
    this.indexMileage += 1
    console.log(this.detailsMileages)
  }

  deleteMileageDetail() {
    this.detailsMileages.splice(this.indexMileage - 1, 1)
    this.indexMileage -= 1
    console.log(this.detailsMileages)
  }

  sendDetails() {
    this.carService.addMileageDetails(this.carId, this.detailsMileages).subscribe({});
  }
}
