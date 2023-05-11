import { Component, Input, OnInit } from '@angular/core';
import { BrandDto } from '../entity/BrandDto';
import { Page } from '../entity/Page';
import { CarService } from '../service/car.service';
import { ActivatedRoute, Router } from '@angular/router';

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
  @Input() brand!: string;
  @Input() model!: string;

  constructor(private carService: CarService,
    private router: Router,
    private activatedRt: ActivatedRoute) {
}

  ngOnInit(): void {
    this.getAllFromServer(this.currPage, this.currPageSize, this.brand, this.model);
  }

  private getAllFromServer(currPage: number, currPageSize: number, brand: string, model: string) {
    this.activatedRt.queryParams.subscribe(params => {
      this.currPage = params['page'],
      this.currPageSize = params['size']
    })
    this.carService.getAllCars().subscribe({
      next: value => {
        this.page = value;
        this.cars = this.page.contents;
        setTimeout(() => this.getAllFromServer(currPage, currPageSize, brand, model), 1000);
      }
    })
  }

  addCarWork(id: number) {
    this.router.navigate([`api/work/car`], {queryParams: {id: id}})
  }

  findByBrand(event: any) {

  }

}
