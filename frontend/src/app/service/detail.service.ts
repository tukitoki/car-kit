import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DetailDto } from '../entity/DetailDto';
import { Observable } from 'rxjs';
import { DetailAddResponse } from '../entity/DetailAddResponse';
import { DetailMileageRequest } from '../entity/DetailMileageRequest';
import { DetailMileageDto } from '../entity/DetailMileageDto';

@Injectable({ providedIn: 'root' })
export class DetailService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/api/detail'
  }

  public showSaveDetail(): Observable<DetailAddResponse> {
    return this.httpClient.get<DetailAddResponse>(`${this.url}/add`)
  }

  public saveDetail(detail: DetailDto) {
    return this.httpClient.post(`${this.url}/add`, detail);
  }

  public getById(id: number): Observable<DetailDto> {
    return this.httpClient.get<DetailDto>(`${this.url}/${id}`)
  }

  public getAllDetails(): Observable<DetailDto[]> {
    return this.httpClient.get<DetailDto[]>(`${this.url}/all`)
  }

  public getDetailsByMileage(dto: DetailMileageRequest): Observable<DetailMileageDto> {
    let params = new HttpParams();
    if (dto.carId !== undefined && dto.mileage !== undefined) {
      params = params.set('carId', dto.carId).set('mileage', dto.mileage);
      console.log(params)
    } else {
      params = params.append('carId', 0)
    }
    return this.httpClient.get<DetailMileageDto>(`${this.url}/mileage-details`, {params})
  }
}
