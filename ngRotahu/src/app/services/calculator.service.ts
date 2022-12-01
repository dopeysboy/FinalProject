import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Obj } from '@popperjs/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Debt } from '../models/debt';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {

  private url = environment.baseUrl + 'api/calculator';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  freeCalc(debt: Debt): Observable<Object>{
    return this.http.post<Object>(`${this.url}/loggedout`, debt).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CalculatorService.freeCalc: error Calculating: ' + err)
        );
      })
    );
  }

  singleCalc(debtId: number): Observable<Object>{
    return this.http.get<Object>(`${this.url}/${debtId}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CalculatorService.singleCalc(): error calculating Debt: ' + err)
        );
      })
    );
  }

  calculateUserDebts(debts: Debt[], residualIncome: number): Observable<Object>{
    return this.http.post<Object>(`${this.url}/${residualIncome}`, debts, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CalculatorService.calculateUserDebts(): error calculating Debts: ' + err)
        )
      })
    );
  }

  calculateUserDebtsFromUser(): Observable<Object>{
    return this.http.get<Object>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CalculatorService.calculateUserDebtsFromUser(): error calculating Debts: ' + err)
        )
      })
    );
  }

  constructor(private authService: AuthService, private http: HttpClient) { }
}

