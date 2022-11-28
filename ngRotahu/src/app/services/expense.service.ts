import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Expense } from '../models/expense';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  private url = environment.baseUrl + 'api/expense';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  create(expense: Expense): Observable<Expense>{
    return this.http.post<Expense>(this.url, expense, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ExpenseService.create(): error creating Expense: ' + err)
        );
      })
    );
  }

  update(newExpense: Expense): Observable<Expense>{
    return this.http.put<Expense>(`${this.url}/${newExpense.id}`, newExpense, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ExpenseService.update(): error updating Expense: ' + err)
        )
      })
    );
  }

  index(): Observable<Expense[]>{
    return this.http.get<Expense[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ExpenseService.index(): error retrieving Expenses: ' + err)
        )
      })
    );
  }

  getById(id: number): Observable<Expense>{
    return this.http.get<Expense>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ExpenseService.getById(): error retrieving Expense: ' + err)
        )
      })
    );
  }

  destroy(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('ExpenseService.delete(): error deleting Expense: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
