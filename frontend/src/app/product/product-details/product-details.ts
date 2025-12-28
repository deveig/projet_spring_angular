import { Component, inject, input, OnInit, signal } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Orderline } from '../../cart/orderline';
import { addItem, calculateTotalPrice } from '../../cart/store/cart.action';

@Component({
  selector: 'app-product-details',
  imports: [FormsModule],
  templateUrl: './product-details.html',
  styleUrl: './product-details.css',
})
export class ProductDetailsComponent implements OnInit {
  protected product = signal<Product>({
    id: 0,
    name: '',
    description: '',
    image: '',
    price: 0.0,
    quantity: 0,
  });
  productService = inject(ProductService);
  activatedRoute = inject(ActivatedRoute);
  productQuantity: number = 1;
  router = inject(Router);
  store = inject(Store)
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.productService.findById(params['id']).subscribe({
        next: (value) => this.product.set(value),
        error: (err) => console.log(err),
      });
    });
  }
  addToCart() {
    if (this.product().quantity >= this.productQuantity && this.product().quantity != 0) {
      // this.product().quantity -= this.quantity();
      console.log(this.productQuantity)
      let orderline: Orderline = {product: this.product(), quantity: this.productQuantity, price: this.product().price * this.productQuantity};
      this.store.dispatch(addItem({orderline: orderline}));
      this.store.dispatch(calculateTotalPrice())
      
    } else {
      console.log("La quantité n'est pas diponible");
    }
  }
}
