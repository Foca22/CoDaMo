import {Component, OnInit} from '@angular/core';
import {CourseService} from './services/course.service';
import {CourseResponse} from './model/course/course-response';
import {AccountResponse} from './model/auth/account-response';
import {AuthenticationService} from './services/authentication.service';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import {AuthorizationResponse} from './model/auth/authorization-response';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  loading = true;

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.authenticationService.getAccount()
      .pipe(
        catchError((err) => {
          this.authenticationService.setFetched();
          this.loading = false;
          this.router.navigate(['/login']).then(r => r);
          throw new Error(err);
        })
      )
      .subscribe(
        (account: AccountResponse) => {
          this.authenticationService.setFetched();
          this.authenticationService.setAccount(account);
          this.authenticationService.refresh(this.authenticationService.tokens.refreshToken)
            .pipe(
              catchError((err) => {
                this.loading = false;
                this.router.navigate(['/login']).then(r => r);
                throw new Error(err);
              })
            ).subscribe(
            (tokens: AuthorizationResponse) => {
              this.authenticationService.setTokens(tokens);
              this.loading = false;
              // this.router.navigate([''])
            }
          );
        }
      );
  }

  isLoggedIn(): boolean {
    console.log(this.authenticationService.isLoggedIn);
    return this.authenticationService.isLoggedIn;
  }
}
