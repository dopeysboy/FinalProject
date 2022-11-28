import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Rating } from '../models/rating';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private url = environment.baseUrl + 'api';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getByLenderId(id: number): Observable<Rating>{
    return this.http.get<Rating>(`${this.url}/debtlender/${id}/rating`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.getById(): error retrieving Rating: ' + err)
        )
      })
    );
  }

  getByUserId(id: number): Observable<Rating>{
    return this.http.get<Rating>(`${this.url}/user/${id}/rating`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.getById(): error retrieving Rating: ' + err)
        )
      })
    );
  }

  getById(id: number): Observable<Rating>{
    return this.http.get<Rating>(`${this.url}/rating/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.getById(): error retrieving Rating: ' + err)
        )
      })
    );
  }

  create(dlid: number, rating: Rating): Observable<Rating>{
    return this.http.post<Rating>(`${this.url}/debtlender/${dlid}`, rating, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.create(): error creating Rating: ' + err)
        );
      })
    );
  }

  update(newRating: Rating): Observable<Rating>{
    return this.http.put<Rating>(`${this.url}/rating/${newRating.id}`, newRating, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.update(): error updating Rating: ' + err)
        )
      })
    );
  }

  destroy(id: number): Observable<void>{
    return this.http.delete<void>(`${this.url}/update/${id}`, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('RatingService.delete(): error deleting Rating: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
