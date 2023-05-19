import { Component, Input, OnInit } from '@angular/core';
import { BrandDto } from '../entity/car/BrandDto';
import { Page } from '../entity/Page';
import { CarService } from '../service/car.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

  url = 'cars'
  page!: Page<BrandDto>
  cars!: BrandDto[];
  currPage: number = 1;
  currPageSize: number = 10;
  @Input() brand: string = ""
  @Input() model: string = ""

  constructor(private carService: CarService,
    private authService: AuthService,
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
    this.getAllFromServer(this.currPage, this.currPageSize, this.brand, this.model);
  }

  private getAllFromServer(currPage: number, currPageSize: number, brand: string, model: string) {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'],
      this.currPageSize = params['size']
    })
    this.carService.getAllCars(this.currPage, this.currPageSize, this.brand, this.model).subscribe({
      next: value => {
        this.page = value;
        this.cars = this.page.contents;
        setTimeout(() => this.getAllFromServer(currPage, currPageSize, brand, model), 1000);
      }
    })
    console.log(this.page)
  }

  addCarWork(id: number) {
    this.router.navigate([`work/car`], {queryParams: {id: id}})
  }

  addDetailToCar(id: number) {
    this.router.navigate([`car/add-details`], {queryParams: {id: id}})
  }

  addMileageDetail(id: number) {
    this.router.navigate([`car/mileage-details`], {queryParams: {id: id}})
  }

  findByBrand(event: any) {
    console.log(this.brand)
    this.carService.getAllCars(this.currPage, this.currPageSize, this.brand, this.model).subscribe({
      next: value => {
        this.page = value;
        this.cars = this.page.contents;
      }
    })
  }
}
