import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { DetailDto } from '../entity/detail/DetailDto';
import { Observable } from 'rxjs';
import { DetailAddResponse } from '../entity/detail/DetailAddResponse';
import { DetailMileageRequest } from '../entity/detail/DetailMileageRequest';
import { DetailMileageDto } from '../entity/detail/DetailMileageDto';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({ providedIn: 'root' })
export class DetailService {

  private url: string;

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
    this.url = `${environment.baseUrl}/api/detail`
  }

  public showSaveDetail(): Observable<DetailAddResponse> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<DetailAddResponse>(`${this.url}/add`, { headers: headers })
  }

  public saveDetail(detail: DetailDto) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/add`, detail, { headers: headers });
  }

  public getById(id: number): Observable<DetailDto> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<DetailDto>(`${this.url}/${id}`,)
  }

  public getAllDetails(): Observable<DetailDto[]> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    return this.httpClient.get<DetailDto[]>(`${this.url}/all`, { headers: headers })
  }

  public getDetailsByMileage(dto: DetailMileageRequest): Observable<DetailMileageDto> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    let params = new HttpParams();
    if (dto.carId !== undefined && dto.mileage !== undefined) {
      params = params.set('carId', dto.carId).set('mileage', dto.mileage);
      console.log(params)
    } else {
      params = params.append('carId', 0)
    }
    return this.httpClient.get<DetailMileageDto>(`${this.url}/mileage-details`, { params: params })
  }
}
