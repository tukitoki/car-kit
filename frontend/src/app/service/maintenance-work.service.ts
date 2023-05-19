import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaintenanceAddResponse } from '../entity/MaintenanceAddResponse';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MaintenanceWorkDto } from '../entity/MaintenanceWorkDto';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MaintenanceWorkService {

  private url: string;

  constructor(private httpClient: HttpClient,
    private authService: AuthService) {
    this.url = `${environment.baseUrl}/api/work`
  }

  addCarWork(id: number, dto: MaintenanceWorkDto) {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);
    return this.httpClient.post(`${this.url}/car/${id}`, dto, { headers: headers })
  }

  showAddCarWork(): Observable<MaintenanceAddResponse> {
    let headers = new HttpHeaders().set('AUTHORIZATION', `Bearer: ${this.authService.accessToken.getValue()}`);

    return this.httpClient.get<MaintenanceAddResponse>(`${this.url}/car`, { headers: headers })
  }
}
