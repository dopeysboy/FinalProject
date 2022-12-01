import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { User } from '../models/user';
import { catchError, Observable, throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class FitnessService {

  private url = environment.baseUrl + "api/fitness";

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  calculateUserFitness(): Observable<number> {
    return this.http.get<number>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.index(): error retrieving Debts: ' + err)
        )
      })
    );
  }


  constructor(private auth : AuthService, private http : HttpClient) { }
}
