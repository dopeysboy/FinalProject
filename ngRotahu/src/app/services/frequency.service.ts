import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Frequency } from '../models/frequency';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FrequencyService {

  private url = environment.baseUrl + 'api/frequency';

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  index(): Observable<Frequency[]>{
    return this.http.get<Frequency[]>(this.url, this.getHttpOptions()).pipe(
      catchError( (err: any) => {
        console.error(err);
        return throwError(
          () => new Error('FrequencyService.index(): error retrieving Frequencies: ' + err)
        )
      })
    );
  }

  constructor(private authService:AuthService, private http:HttpClient) { }
}
