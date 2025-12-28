import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export abstract class GenericService<T> {
  url = environment.BACKEND_URL;
  constructor(protected http: HttpClient, protected path: string) {
  }

  findAll(): Observable<T[]> {
    return this.http.get<T[]>(this.url+this.path);
  }

  findById(id: number): Observable<T> {
    return this.http.get<T>(`${this.url}${this.path}/${id}`);
  }

  save(obj: T): Observable<T> {
    return this.http.post<T>(this.url + this.path, obj);
  }

  update(id: number, obj: T): Observable<T> {
    return this.http.put<T>(`${this.url}${this.path}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}${this.path}/${id}`);
  }
}
