import { Component } from '@angular/core';
import { CarService } from '../service/car.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CarDto } from '../entity/CarDto';
import { BrandDto } from '../entity/BrandDto';
import { ModelDto } from '../entity/ModelDto';

@Component({
  selector: 'app-car-add',
  templateUrl: './car-add.component.html',
  styleUrls: ['./car-add.component.css']
})
export class CarAddComponent {

  cars!: BrandDto[];
  selectedBrand!: BrandDto;
  selectedModel!: ModelDto | null;
  car!: CarDto;
  brand: string = ''
  model: string = ''

  constructor(
    private carSerivce: CarService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    carSerivce.getAllCars().subscribe({
      next: value => this.cars = value.contents
    })
  }

  ngOnInit(): void {
    this.car = {
      id: null,
      brand: '',
      model: '',
      modificationDto: {
        id: -1,
        name: '',
        engineModel: '',
        yearFrom: null,
        yearTo: null
      }
    }
  }

  saveCar() {
    this.carSerivce.saveCar({
      id: null,
      brand: this.selectedBrand.brand,
      model: this.selectedModel!.model,
      modificationDto: {
        id: -1,
        name: this.car.modificationDto.name,
        engineModel: this.car.modificationDto.engineModel,
        yearFrom: this.car.modificationDto.yearFrom,
        yearTo: this.car.modificationDto.yearTo
      }
    }).subscribe();
  }

  createBrand() {
    this.cars.push({brand: this.brand, models: []})
  }

  createModel() {
    this.selectedBrand.models.push({model: this.model, modifications: []})
  }


  clearOnBrand(value: any) {
    this.selectedModel = null;
    this.car.modificationDto.name = null;
    this.car.modificationDto.engineModel = null;
    this.car.modificationDto.yearFrom = null;
    this.car.modificationDto.yearTo = null;
  }

  clearOnModel(value: any) {
    this.car.modificationDto.name = null;
    this.car.modificationDto.engineModel = null;
    this.car.modificationDto.yearFrom = null;
    this.car.modificationDto.yearTo = null;
  }
}
