import { Injectable } from '@angular/core';
import { BaseApiService } from '../base-api.service';
import { LoginDTO } from '../../../shared/models/LoginDTO';
import { tap } from 'rxjs';
import { UserDto } from '../../../shared/models/UserDto';
import { catchError, map, Observable, of } from 'rxjs';

const TOKEN_KEY = 'flux_token';

export interface LoginResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseApiService<LoginDTO> {
  protected override readonly endpoint = this.api + '/auth';

  constructor() {
    super();
  }

  login(credentials: LoginDTO) {
    return this.http.post<LoginResponse>(this.endpoint + '/login', credentials).pipe(
      tap((res) => {
        localStorage.setItem(TOKEN_KEY, res.token);
      })
    );
  }

  getUser(): Observable<UserDto> {
    return this.http.get<UserDto>(this.endpoint + '/me');
  }

  /**
   * Verifies current session. Returns true if valid, false otherwise.
   * Caller is responsible for navigation.
   */
  verifySession(): Observable<boolean> {
    return this.getUser().pipe(
      map(user => {
        return user !== null;
      }),
      catchError(() => of(false))
    );
  }
}
