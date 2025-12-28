import { Component, inject, signal } from '@angular/core';
import { Orderline } from './orderline';
import { Store } from '@ngrx/store';
import { selectCart, selectTotalPrice } from './store/cart.selector';
import { addItem, calculateTotalPrice, clear, deleteItem, updateItem } from './store/cart.action';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../product/product-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  imports: [FormsModule],
  templateUrl: './cart.html',
  styleUrl: './cart.css',
})
export class CartComponent {
  orderLines = signal<Orderline[]>([]);
  totalPrice = signal<number>(0);
  productService = inject(ProductService);
  router = inject(Router);
  constructor(private store: Store) {
    store.select(selectCart).subscribe((value) => this.orderLines.set(value));
    store.select(selectTotalPrice).subscribe((value) => this.totalPrice.set(value));
  }
  changeQuantity(event: Event, index: number) {
    const quantity = Number((event.target as HTMLInputElement).value);
    const product = this.orderLines().at(index)!.product;
    const orderline: Orderline = {
      product: product,
      quantity: quantity,
      price: product.price * quantity,
    };
    this.store.dispatch(updateItem({ orderline: orderline }));
    this.store.dispatch(calculateTotalPrice());
    this.store.select(selectTotalPrice).subscribe((value) => this.totalPrice.set(value));
  }
  delete(index: number) {
    this.store.dispatch(deleteItem({ id: this.orderLines().at(index)?.product.id! }));
    this.store.dispatch(calculateTotalPrice());
    this.store.select(selectTotalPrice).subscribe((value) => this.totalPrice.set(value));
  }
  validate() {
    if (this.totalPrice() != 0) {
      alert('Votre commande a été prise en compte !');
      this.store.dispatch(clear());
      this.router.navigateByUrl('/');
    }
  }
}
