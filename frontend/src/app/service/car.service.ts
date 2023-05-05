import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BrandDto } from '../entity/BrandDto';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/api/car'
  }

  public getAllCars(): Observable<BrandDto[]> {
    return this.httpClient.get<BrandDto[]>(`${this.url}/all`)
  }
}
