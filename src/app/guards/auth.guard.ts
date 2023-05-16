import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router
  ){}

  canActivate(): boolean {
    if(localStorage.getItem("authCode") === "101" && localStorage.getItem("authCode") !== null && localStorage.getItem("authCode") !== "" ) {
      return true;
    } 

    this.router.navigate(['login']);
    return false;
  }
  
}
