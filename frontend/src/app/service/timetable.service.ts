import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestTimeResponse } from '../entity/request/RequestTimeResponse';
import { RequestApplyResponse } from '../entity/request/RequestApplyResponse';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { RequestDto } from '../entity/request/RequestDto';
import { Page } from '../entity/Page';

@Injectable({
  providedIn: 'root'
})
export class TimetableService {

  private url: string;

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {

    this.url = `${environment.baseUrl}/api/request`
  }

  public getAllRequests(currPage: number = 1, currPageSize: number = 10, order: string): Observable<Page<RequestDto>> {
    let params = {
      'pageNumber': currPage,
      'pageSize': currPageSize,
      'order': order
    };
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    console.log(this.authService.accessToken.getValue())
    return this.httpClient.get<Page<RequestDto>>(`${this.url}/all`, { params: params });
  }

  public sendRequest(requestApply: RequestApplyResponse) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/apply`, requestApply)
  }

  public infoRequest(carId: number | undefined, detailIds: (number | null)[]): Observable<RequestTimeResponse> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer ${this.authService.accessToken.getValue()}`);
    let params = new HttpParams();
    if (carId !== undefined) {
      params = params.set('carId', carId)
    }
    for (const id of detailIds) {
      if (id !== null) {
        params = params.append('detailIds', id);
      }
    }
    return this.httpClient.get<RequestTimeResponse>(`${this.url}/apply`, { params: params })
  }
}
