import {Component, OnInit} from '@angular/core';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication.service';
import {AccountResponseWithTokens} from '../../model/auth/account-response-with-tokens';
import {LoginRequest} from '../../model/auth/login-request';
import {ToasterService} from '../../services/toaster.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(4)])
  });

  constructor(private authService: AuthenticationService,
              private router: Router,
              private toasterService: ToasterService) {
  }

  ngOnInit(): void {
  }

  login(): void {
    this.authService.login({email: this.email.value, password: this.password.value} as LoginRequest)
      .pipe(
        catchError((err) => {
          this.toasterService.showError('Email not found');
          return throwError(() => err);
        })
      )
      .subscribe((accountWithTokens: AccountResponseWithTokens) => {
        this.authService.setAccountWithTokens(accountWithTokens);
        this.router.navigate(['']);
      });
  }

  // tslint:disable-next-line:typedef
  get email() {
    return this.loginForm.get('email');
  }

  // tslint:disable-next-line:typedef
  get password() {
    return this.loginForm.get('password');
  }
}
