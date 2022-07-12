import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { LoginData } from '../additional-models/LoginData';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Employee, UserWrapper } from '../generated';
import { NavigationService } from './navigation.service';

export const EMP_KEY: string = 'EMP_KEY';
export const ACCESS_TOKEN_KEY: string = 'ACCESS_TOKEN_KEY';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService implements OnInit{

  constructor(
    private http: HttpClient,
    public navigationService: NavigationService) {}

  ngOnInit() {}

  login(loginData: LoginData): Observable<UserWrapper> {
    return this.http.post<UserWrapper>(`${environment.basePath}/login`, loginData);
  }

  register(employee: Employee): Observable<any> {
    return this.http.post(`${environment.basePath}/employees/createEmployee`, employee);
  }

  public storeEmployee(emp: Employee): void {
    localStorage.setItem(EMP_KEY, JSON.stringify(emp));
  }

  public getEmployee(): Employee {
    return JSON.parse(localStorage.getItem(EMP_KEY));
  }

  public deleteEmployee(): void {
    localStorage.removeItem(EMP_KEY);
  }

  public storeAccessToken(token: string): void {
    localStorage.setItem(ACCESS_TOKEN_KEY, token);
  }

  public getAccessToken(): string {
    return localStorage.getItem(ACCESS_TOKEN_KEY);
  }

  public deleteAccessToken(): void {
    localStorage.removeItem(ACCESS_TOKEN_KEY);
  }

  public localLogin(emp: Employee, token: string): void {
    this.storeEmployee(emp);
    this.storeAccessToken(token);
  }

  public logout(): void {
    this.deleteEmployee();
    this.deleteAccessToken();
    this.navigationService.goTo('/login');
  }

  public isLoggedIn(): boolean {
    return (this.getEmployee() !== null && this.getEmployee() !== undefined && this.getAccessToken() !== null && this.getAccessToken() !== undefined);
  }

  public hasAnyRole(roles: string[]): boolean {
    if(this.isLoggedIn()) {
      const empRoles: string[] = this.getEmployee().roles.split(',');
      for (let index = 0; index < empRoles.length; index++) {
        if(roles.includes(empRoles[index].trim())) {
          return true;
        }
      }
    } else {
      return false;
    }
  }

  public hasAnyPermission(permissions: string[]): boolean {
    if(this.isLoggedIn()) {
      const employeePermissions: string[] = this.getEmployee().permissions.split(',');
      for (let index = 0; index < employeePermissions.length; index++) {
        if(permissions.includes(employeePermissions[index].trim())) {
          return true;
        }
      }
    } else {
      return false;
    }
  }

}
