import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestTimeResponse } from '../entity/RequestTimeResponse';
import { RequestApplyResponse } from '../entity/RequestApplyResponse';

@Injectable({
  providedIn: 'root'
})
export class TimetableService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'https://backend-tukitoki.cloud.okteto.net/api/request'
  }

  public sendRequest(requestApply: RequestApplyResponse) {
    return this.httpClient.post(`${this.url}/apply`, requestApply)
  }

  public infoRequest(carId: number | undefined, detailIds: (number | null)[]): Observable<RequestTimeResponse> {
    let params = new HttpParams();
    if (carId !== undefined) {
      params = params.set('carId', carId)
    }
    for (const id of detailIds) {
      if (id !== null) {
        params = params.append('detailIds', id);
      }
    }
    return this.httpClient.get<RequestTimeResponse>(`${this.url}/apply`, {params})
  }
}
