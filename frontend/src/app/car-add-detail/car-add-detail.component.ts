import { Component } from '@angular/core';
import { DetailService } from '../service/detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailDto } from '../entity/detail/DetailDto';
import { CarAddDetailsRequest } from '../entity/car/CarAddDetailsRequest';
import { CarService } from '../service/car.service';
import { AuthService } from '../service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-car-add-detail',
  templateUrl: './car-add-detail.component.html',
  styleUrls: ['./car-add-detail.component.css']
})
export class CarAddDetailComponent {

  details!: DetailDto[];
  detailToSend!: CarAddDetailsRequest;
  currIndex: number = 0;
  selected!: boolean;
  carId!: number;

  constructor(private detailService: DetailService,
    private authService: AuthService,
    private carService: CarService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
}

  ngOnInit(): void {
    if (this.authService.username.getValue() == null) {
      Swal.fire("Login to see this page").then(() => this.router.navigate(['/login']));
      return;
    } else if (!this.authService.checkAuthoritiy('CARS_EDIT')) {
      Swal.fire("U can't access this page").then(() => this.router.navigate(['/main']))
      return;
    }
    this.detailService.getAllDetails().subscribe({
      next: value => this.details = value
    })
    this.activatedRt.queryParams.subscribe(params => {
      this.carId = params['id']
    })
    this.carService.getCarDetails(this.carId).subscribe({
      next: value => {
        for (let i = 0; i < value.length; i++) {
          let ind = this.details.map(e => e.id).indexOf(value[i].id);
          if (ind != -1) {
            this.details.splice(ind, 1);
          }
        }
      }
    })
    this.detailToSend = {
      detailIds: []
    }
  }

  toDetail(id: number | null, event: MouseEvent) {
    event.preventDefault();
    window.open("http://localhost:4200/detail/" + id);
  }

  checkSelected(event: any, id: number | null) {
    this.selected = (event.target as HTMLInputElement).checked;
    if (this.selected) {
      this.detailToSend.detailIds.push(id);
    } else {
      this.detailToSend.detailIds.splice(this.detailToSend.detailIds.indexOf(id), 1);
    }
  }

  ifPresent(id: number | null) {
    return this.detailToSend.detailIds.indexOf(id) != -1
  }

  sendDetails() {
    this.carService.addDetailsToCar(this.carId, this.detailToSend).subscribe();
    this.router.navigate([`cars`])
  }
}
