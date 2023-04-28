import { Component } from '@angular/core';
import { CarService } from '../service/car.service';
import { ActivatedRoute, Router } from '@angular/router';
import { __values } from 'tslib';
import { CarDtoResponse } from '../entity/CarDtoResponse';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent {

  cars!: CarDtoResponse[];

  constructor(
    private carService: CarService,
    private router: Router,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.carService.getAllCars().subscribe({
      next: value => this.cars = value
    })
  }
}
