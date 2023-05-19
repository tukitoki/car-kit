import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BrandDto } from '../entity/car/BrandDto';
import { CarDto } from '../entity/car/CarDto';
import { Page } from '../entity/Page';
import { DetailMileageAdd } from '../entity/detail/DetailMileageAdd';
import { CarAddDetailsRequest } from '../entity/car/CarAddDetailsRequest';
import { DetailDto } from '../entity/detail/DetailDto';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private url: string;

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
    this.url = `${environment.baseUrl}/api/car`
  }

  public getAllCars(currPage: number = 1, currPageSize: number = 10, brand: string = '', model: string = ''): Observable<Page<BrandDto>> {
    let params = {
      'pageNumber': currPage,
      'pageSize': currPageSize,
      'brand': brand,
      'modle': model
    };
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<Page<BrandDto>>(`${this.url}/all`, { params: params , headers: headers });
  }

  public getCarById(id: number): Observable<CarDto> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<CarDto>(`${this.url}/${id}`, { headers: headers });
  }

  public getCarDetails(carId: number): Observable<DetailDto[]> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<DetailDto[]>(`${this.url}/${carId}/details`, { headers: headers })
  }

  public addDetailsToCar(carId: number, dto: CarAddDetailsRequest) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/${carId}/add-details`, dto, { headers: headers })
  }

  public saveCar(car: CarDto) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}`, car, {headers: headers});
  }

  public addMileageDetails(id: number, details: DetailMileageAdd[]) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/${id}/add-mileage`, details, { headers: headers });
  }
}
