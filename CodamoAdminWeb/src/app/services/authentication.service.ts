import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AccountResponseWithTokens} from '../model/auth/account-response-with-tokens';
import {AccountResponse} from '../model/auth/account-response';
import {LoginRequest} from '../model/auth/login-request';
import {AuthorizationResponse} from '../model/auth/authorization-response';

@Injectable({providedIn: 'root'})
export class AuthenticationService {

  fetched = false;
  isLoggedIn = false;
  currentAccount: AccountResponse | undefined;
  tokens: AuthorizationResponse | undefined;

  constructor(private httpClient: HttpClient) {
    if (localStorage.getItem('accessToken')) {
      this.tokens = {
        accessToken: localStorage.getItem('accessToken'),
        refreshToken: localStorage.getItem('refreshToken')
      } as AuthorizationResponse;
    }
  }

  setFetched(): void {
    this.fetched = true;
  }

  setAccountWithTokens(accountWithTokens: AccountResponseWithTokens): void {
    this.setAccount(accountWithTokens.account);
    this.setTokens(accountWithTokens.tokens);
  }

  setAccount(account: AccountResponse): void {
    this.isLoggedIn = true;
    this.currentAccount = account;
  }

  setTokens(tokens: AuthorizationResponse): void {
    this.tokens = tokens;
    localStorage.setItem('accessToken', tokens.accessToken);
    localStorage.setItem('refreshToken', tokens.refreshToken);
  }

  login(loginRequest: LoginRequest): Observable<AccountResponseWithTokens> {
    return this.httpClient.post<AccountResponseWithTokens>('/api/auth/login', loginRequest);
  }

  getAccount(): Observable<AccountResponse> {
    return this.httpClient.get<AccountResponse>('/api/auth');
  }

  refresh(refreshToken: string): Observable<AuthorizationResponse> {
    return this.httpClient.post<AuthorizationResponse>(`/api/auth/refresh/${refreshToken}`, {});
  }

  logout(): void {
    this.isLoggedIn = false;
    this.currentAccount = undefined;
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }
}
