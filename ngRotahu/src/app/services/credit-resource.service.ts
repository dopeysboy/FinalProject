import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreditResource } from '../models/credit-resource';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CreditResourceService {

  url = environment.baseUrl + 'creditresource';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<CreditResource[]>{
    return this.http.get<CreditResource[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CreditResourceService.index(): error retrieving CreditResources: ' + err)
        );
      })
    );
  }

  getById(id: number): Observable<CreditResource>{
    return this.http.get<CreditResource>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CreditResourceService.getById(): error retrieving CreditResource: ' + err)
        );
      })
    );
  }

  create(newCr: CreditResource): Observable<CreditResource>{
    return this.http.post<CreditResource>(this.url, newCr, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CreditResourceService.create(): error creating CreditResource: ' + err)
        );
      })
    );
  }

  update(oldId: number, newCr: CreditResource): Observable<CreditResource>{
    return this.http.put<CreditResource>(`${this.url}/${oldId}`, newCr, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CreditResourceService.update(): error updating CreditResource: ' + err)
        );
      })
    );
  }
  constructor(private http: HttpClient, private authService: AuthService) { }
}
