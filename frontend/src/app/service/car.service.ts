import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BrandDto } from '../entity/BrandDto';
import { CarDto } from '../entity/CarDto';
import { Page } from '../entity/Page';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/api/car'
  }

  public getAllCars(currPage: number = 1, currPageSize: number = 10, brand: string = '', model: string = ''): Observable<Page<BrandDto>> {
    let params = {
      'pageNumber': currPage,
      'pageSize': currPageSize,
      'brand': brand,
      'modle': model
    };
    return this.httpClient.get<Page<BrandDto>>(`${this.url}/all`, { params: params });
  }

  public getCarById(id: number): Observable<CarDto> {
    return this.httpClient.get<CarDto>(`${this.url}/${id}`)
  }

  public saveCar(car: CarDto) {
    return this.httpClient.post(`${this.url}`, car)
  }
}
