import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Debt } from '../models/debt';
import { DebtLender } from '../models/debt-lender';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DebtLenderService {

  private url = environment.baseUrl + 'api/debtlender';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<DebtLender[]>{
    return this.http.get<DebtLender[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtLenderService.index(): error retreiving DebtLenders: ' + err)
        );
      })
    );
  }

  getById(id: number): Observable<DebtLender>{
    return this.http.get<DebtLender>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtLenderService.getById(): error retreiving DebtLender: ' + err)
        );
      })
    );
  }

  addDebt(debtLenderId: number, debt: Debt): Observable<DebtLender>{
    return this.http.put<DebtLender>(`${this.url}/${debtLenderId}/add/debt/${debt.id}`, debt, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtLenderService.addDebt(): error updating DebtLender: ' + err)
        );
      })
    );
  }

  removeDebt(debtLenderId: number, debt: Debt): Observable<DebtLender>{
    return this.http.put<DebtLender>(`${this.url}/${debtLenderId}/remove/debt/${debt.id}`, debt, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtLenderService.removeDebt(): error updating DebtLender: ' + err)
        );
      })
    );
  }


  constructor(private authService: AuthService, private http: HttpClient) { }
}
