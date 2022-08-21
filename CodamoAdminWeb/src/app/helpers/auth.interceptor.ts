import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {catchError, switchMap} from 'rxjs/operators';
import {AuthenticationService} from '../services/authentication.service';
import {AuthorizationResponse} from '../model/auth/authorization-response';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private authService: AuthenticationService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
    let authReq = req;
    const accessToken = this.authService.tokens?.accessToken;
    if (accessToken != null) {
      authReq = this.addTokenHeader(req, accessToken);
    }

    return next.handle(authReq).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 403) {
        if (!this.isRefreshing) {
          this.isRefreshing = true;
          this.refreshTokenSubject.next(null);

          const potentialRefreshToken = this.authService.tokens?.refreshToken;

          if (potentialRefreshToken) {
            return this.handle403Error(authReq, next, potentialRefreshToken);
          }
        }
      }
      return throwError(() => error);
    }));
  }

  private handle403Error(request: HttpRequest<any>, next: HttpHandler, token: string): Observable<any> {
    return this.authService.refresh(token).pipe(
      switchMap((tokens: AuthorizationResponse) => {
        this.isRefreshing = false;

        this.authService.setTokens(tokens);
        this.refreshTokenSubject.next(tokens.accessToken);

        return next.handle(this.addTokenHeader(request, tokens.accessToken));
      }),
      catchError((err) => {
        this.isRefreshing = false;

        this.authService.logout();
        return throwError(() => err);
      })
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string): HttpRequest<any> {
    return request.clone({headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)});
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
