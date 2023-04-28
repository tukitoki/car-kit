import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CarDtoResponse } from '../entity/CarDtoResponse';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/api/car'
  }

  public getAllCars(): Observable<CarDtoResponse[]> {
    return this.httpClient.get<CarDtoResponse[]>(`${this.url}/all`)
  }
}
