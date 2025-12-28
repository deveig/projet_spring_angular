import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericService } from '../generic-service';
import { UserModel } from '../user/user-model';
import { Observable } from 'rxjs';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root',
})
export class AdminService extends GenericService<UserModel> {
    constructor(http: HttpClient) {
    super(http, '/users');
  }
  override save(user: UserModel): Observable<UserModel> {
    // super.save(user)
    return this.http.post<UserModel>(`${this.url}${this.path}/register-admin`, user)
  }
  findByUsernameAndPassword(user: UserModel): Observable<Token> {
    return this.http.post<Token>(`${this.url}${this.path}/login`, user);
  }
}
