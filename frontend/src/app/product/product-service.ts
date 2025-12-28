import { Injectable } from '@angular/core';
import { GenericService } from '../generic-service';
import { Product } from './product';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService extends GenericService<Product> {
  constructor(http: HttpClient) {
    super(http, "/products")
  }
  findByCriteria(criteria: string): Observable<Product[]>{
    return this.http.get<Product[]>(`${this.url}${this.path}/search/criteria?criteria=${criteria}`)
  }
}
