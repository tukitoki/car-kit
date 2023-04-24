import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DetailDto } from '../entity/DetailDto';
import { Observable } from 'rxjs';
import { DetailAddResponse } from '../entity/DetailAddResponse';

@Injectable({providedIn: 'root'})
export class DetailService {

    private url: string;

    constructor(private httpClient: HttpClient) {
        this.url = 'http://localhost:8080/api/detail'
     }
    
    public showSaveDetail(): Observable<DetailAddResponse> {
        return this.httpClient.get<DetailAddResponse>(`${this.url}/add`)
    }

    public saveDetail(detail: DetailDto) {

    }

    public getById(id: number) {

    }
}