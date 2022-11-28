import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DebtType } from '../models/debt-type';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DebtTypeService {
  private url = environment.baseUrl + 'api/debttype';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<DebtType[]>{
    return this.http.get<DebtType[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtTypeService.index(): error retreiving DebtTypes: ' + err)
        );
      })
    );
  }

  constructor(private authService: AuthService, private http: HttpClient) { }
}
