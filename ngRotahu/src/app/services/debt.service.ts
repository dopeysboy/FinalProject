import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Debt } from '../models/debt';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DebtService {

  private url = environment.baseUrl + 'api/debt';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  create(debt: Debt): Observable<Debt>{
    return this.http.post<Debt>(this.url, debt, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.create(): error creating Debt: ' + err)
        );
      })
    );
  }

  update(newDebt: Debt): Observable<Debt>{
    return this.http.put<Debt>(`${this.url}/${newDebt.id}`, newDebt, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.update(): error updating Debt: ' + err)
        )
      })
    );
  }

  index(): Observable<Debt[]>{
    return this.http.get<Debt[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.index(): error retrieving Debts: ' + err)
        )
      })
    );
  }

  getById(id: number): Observable<Debt>{
    return this.http.get<Debt>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.getById(): error retrieving Debt: ' + err)
        )
      })
    );
  }


  destroy(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('DebtService.delete(): error deleting Debt: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
