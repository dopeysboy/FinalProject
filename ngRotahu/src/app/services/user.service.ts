import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = environment.baseUrl + 'user';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  disable(user: User): Observable<void>{
    return this.http.put<void>(`${this.url}/disable`, user, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('UserService.disable(): error disabling User: ' + err)
        )
      })
    );
  }

  changePassword(newPassword: string): Observable<User>{
    return this.http.put<User>(`${this.url}/changePassword`, newPassword, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('UserService.changePassword(): error changing password: ' + err)
        )
      })
    );
  }

  updateAccount(user: User): Observable<User>{
    return this.http.put<User>(`${this.url}/updateAccount`, user, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('UserService.updateAccount(): error updating account: ' + err)
        )
      })
    );
  }

  index(): Observable<User[]>{
    return this.http.get<User[]>(`${this.url}/admin`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('UserService.index(): error retrieving Users: ' + err)
        )
      })
    );
  }

  toggleEnable(user: User): Observable<void>{
    return this.http.put<void>(`${this.url}/admin/toggleEnable`, user.username, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('UserService.toggleEnable(): error toggling enabled: ' + err)
        )
      })
    );
  }

}
