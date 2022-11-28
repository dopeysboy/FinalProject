import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Income } from '../models/income';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  private url = environment.baseUrl + 'api/income';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  create(income: Income): Observable<Income>{
    return this.http.post<Income>(this.url, income, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('IncomeService.create(): error creating Income: ' + err)
        );
      })
    );
  }

  update(newIncome: Income): Observable<Income>{
    return this.http.put<Income>(`${this.url}/${newIncome.id}`, newIncome, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('IncomeService.update(): error updating Income: ' + err)
        )
      })
    );
  }

  index(): Observable<Income[]>{
    return this.http.get<Income[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('IncomeService.index(): error retrieving Incomes: ' + err)
        )
      })
    );
  }

  getById(id: number): Observable<Income>{
    return this.http.get<Income>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('IncomeService.getById(): error retrieving Income: ' + err)
        )
      })
    );
  }

  destroy(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('IncomeService.delete(): error deleting Income: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
