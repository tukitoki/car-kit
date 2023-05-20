import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserLoginDto } from '../entity/user/UserLoginDto';
import jwt_decode from "jwt-decode";
import { JwtDto } from '../entity/user/JwtDto';
import { decode } from 'punycode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  accessToken = new BehaviorSubject<string | null>(localStorage.getItem('access-jwt'));
  username = new BehaviorSubject<string | null>(localStorage.getItem('username'));
  authorities = new BehaviorSubject<string[]>(JSON.parse(localStorage.getItem('authorities') || '{}'));
  private timer?: ReturnType<typeof setTimeout>;
  private errors = new Subject<string | null>();
  private url: string;

  constructor(private httpClient: HttpClient) {
    this.url = `${environment.baseUrl}/api/auth`
  }

  public loginUser(user: UserLoginDto): Observable<string | null> {
    let tokens = this.httpClient.post<JwtDto>(`${this.url}/login`, user);
    tokens.subscribe({
      next: response => {
        localStorage.setItem('access-jwt', response.accessToken);
        localStorage.setItem('refresh-jwt', response.refreshToken);
        this.updateSubjects();
        this.errors.next(null);
      },
      error: (err: HttpErrorResponse) => {
        if (err.status == 0) {
          setTimeout(() => this.loginUser(user), environment.retryDelay);
        } else {
          this.errors.next(err.error.message);
        }
      }
    });
    return this.errors;
  }

  public logout() {
    localStorage.removeItem('access-jwt');
    localStorage.removeItem('refresh-jwt');
    localStorage.removeItem('username');
    localStorage.removeItem('authorities');
    this.updateSubjects();
  }

  public checkAuthoritiy(authority: string) {
    console.log(this.authorities.getValue());
    return this.authorities.getValue().includes(authority);
  }

  private updateSubjects() {
    let accessToken = localStorage.getItem('access-jwt');
    if (accessToken) {
      this.accessToken.next(accessToken);
      let decodedAccess: any = jwt_decode(accessToken);
      localStorage.setItem('username', decodedAccess.sub);
      localStorage.setItem('authorities', JSON.stringify(decodedAccess.authorities));
      this.username.next(localStorage.getItem('username'));
      this.authorities.next(JSON.parse(localStorage.getItem('authorities') || '{}'));
      this.timer = setTimeout(() => this.refresh(), Math.max(decodedAccess.exp * 1000 - Date.now(), 0));
    } else {
      this.accessToken.next('');
      this.username.next(null);
      this.authorities.next([]);
      clearTimeout(this.timer);
    }
  }

  private refresh() {
    let refreshToken = localStorage.getItem('refresh-jwt');
    console.log(refreshToken)
    if (refreshToken == null) {
      this.logout();
      return;
    }
    let decodedRefresh: any = jwt_decode(refreshToken);
    if (decodedRefresh.exp * 1000 > Date.now()) {
      this.refreshToken(refreshToken);
    } else {
      this.logout();
    }
  }

  private refreshToken(refreshToken: string) {
    this.httpClient.post<JwtDto>(`${this.url}/refresh`, refreshToken).subscribe({
      next: (response) => {
        localStorage.setItem('access-jwt', response.accessToken);
        localStorage.setItem('refresh-jwt', response.refreshToken);
        this.updateSubjects();
      }, error: (err: HttpErrorResponse) => {
        if (err.status == 0) {
          setTimeout(() => this.refreshToken(refreshToken), environment.retryDelay);
        } else {
          this.logout();
        }
      }
    });
  }
}
