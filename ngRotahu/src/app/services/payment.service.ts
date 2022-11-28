import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Payment } from '../models/payment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private url = environment.baseUrl + 'api/payment';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  create(payment: Payment): Observable<Payment>{
    return this.http.post<Payment>(this.url, payment, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PaymentService.create(): error creating Payment: ' + err)
        );
      })
    );
  }

  update(newPayment: Payment): Observable<Payment>{
    return this.http.put<Payment>(`${this.url}/${newPayment.id}`, newPayment, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PaymentService.update(): error updating Payment: ' + err)
        )
      })
    );
  }

  index(): Observable<Payment[]>{
    return this.http.get<Payment[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PaymentService.index(): error retrieving Payments: ' + err)
        )
      })
    );
  }

  getById(id: number): Observable<Payment>{
    return this.http.get<Payment>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PaymentService.getById(): error retrieving Payment: ' + err)
        )
      })
    );
  }

  destroy(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('PaymentService.delete(): error deleting Payment: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
