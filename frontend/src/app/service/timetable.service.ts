import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestTimeResponse } from '../entity/request/RequestTimeResponse';
import { RequestApplyResponse } from '../entity/request/RequestApplyResponse';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TimetableService {

  private url: string;

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {

    this.url = `${environment.baseUrl}/api/request`
  }

  public sendRequest(requestApply: RequestApplyResponse) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/apply`, requestApply, { headers: headers })
  }

  public infoRequest(carId: number | undefined, detailIds: (number | null)[]): Observable<RequestTimeResponse> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    let params = new HttpParams();
    if (carId !== undefined) {
      params = params.set('carId', carId)
    }
    for (const id of detailIds) {
      if (id !== null) {
        params = params.append('detailIds', id);
      }
    }
    return this.httpClient.get<RequestTimeResponse>(`${this.url}/apply`, { params: params, headers: headers })
  }
}
