import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from '../models/category';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private url = environment.baseUrl + 'api/category';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Category[]>{
    return this.http.get<Category[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CategoryService.index(): error retreiving Categories: ' + err)
        );
      })
    );
  }

  getById(id: number): Observable<Category>{
    return this.http.get<Category>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('CategoryService.getById(): error retreiving Category: ' + err)
        );
      })
    );
  }

  constructor(private authService: AuthService, private http: HttpClient) { }
}
