import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {
  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.authenticationService.fetched) {
      return true;
    }
    if (route.url.map(a => a.path).includes('login')) {
      if (this.authenticationService.isLoggedIn) {
        return false;
      } else {
        return true;
      }
    } else {
      if (this.authenticationService.isLoggedIn) {
        // logged in so return true
        return true;
      } else {
        this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}}).then(r => r);
        return false;
      }
    }


    // not logged in so redirect to login page with the return url
    // this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return true;
  }
}
