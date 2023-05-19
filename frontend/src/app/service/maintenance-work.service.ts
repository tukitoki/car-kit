import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaintenanceAddResponse } from '../entity/MaintenanceAddResponse';
import { HttpClient } from '@angular/common/http';
import { MaintenanceWorkDto } from '../entity/MaintenanceWorkDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceWorkService {

  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = `${environment.baseUrl}/api/work`
  }

  addCarWork(id: number, dto: MaintenanceWorkDto) {
    return this.httpClient.post(`${this.url}/car/${id}`, dto)
  }

  showAddCarWork(): Observable<MaintenanceAddResponse> {
    return this.httpClient.get<MaintenanceAddResponse>(`${this.url}/car`)
  }
}
