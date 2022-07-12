import { Injectable } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  constructor(
    private router: Router,
  ) { }

  goTo(url: string) {
    this.router.navigate([url]);
  }

  goToWithId(url: string, id: number) {
    const extra: NavigationExtras = {
      queryParams: {id: id}
    };
    this.router.navigate([url], extra);
  }

  goToWithParams(url: string, params: any) {
    const extra: NavigationExtras = {
      queryParams: {param: params}
    };
    this.router.navigate([url], extra);
  }
}
