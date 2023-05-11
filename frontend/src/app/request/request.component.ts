import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BrandDto } from '../entity/BrandDto';
import { DetailMileageRequest } from '../entity/DetailMileageRequest';
import { ModelDto } from '../entity/ModelDto';
import { ModificationDto } from '../entity/ModificationDto';
import { CarService } from '../service/car.service';
import { DetailService } from '../service/detail.service';
import { DetailMileageDto } from '../entity/DetailMileageDto';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent {

  cars!: BrandDto[];
  brand!: BrandDto;
  model?: ModelDto;
  modification?: ModificationDto;
  mileage?: number;
  request!: DetailMileageRequest;
  mileageDto?: DetailMileageDto;

  constructor(
    private carService: CarService,
    private detailService: DetailService,
    private router: Router,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.carService.getAllCars().subscribe({
      next: value => {
        this.cars = value.contents
      }
    })
  }

  clearOnBrand(value: any) {
    this.model = undefined;
    this.modification = undefined;
    this.mileageDto = undefined;
  }

  clearOnModel(value: any) {
    this.modification = undefined;
    this.mileageDto = undefined;
  }

  getDetails() {
    this.request = {
      mileage: this.mileage,
      carId: this.modification?.id
    }
    this.detailService.getDetailsByMileage(this.request).subscribe({
      next: value => {
        this.mileageDto = value
      }
    })
  }
}
