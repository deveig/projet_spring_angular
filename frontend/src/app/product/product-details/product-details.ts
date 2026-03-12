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
  message = "";
  productService = inject(ProductService);
  activatedRoute = inject(ActivatedRoute);
  productQuantity: number = 1;
  router = inject(Router);
  store = inject(Store);
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.productService.findById(params['id']).subscribe({
        next: (value) => this.product.set(value),
        error: (err) => //console.log(err)
        console.log("Internal Server Error"),
      });
    });
  }
  addToCart() {
    if (localStorage.getItem('user')) {
      if (this.product().quantity >= this.productQuantity && this.product().quantity != 0) {
        let orderline: Orderline = {
          product: this.product(),
          quantity: this.productQuantity,
          price: this.product().price * this.productQuantity,
        };
        this.store.dispatch(addItem({ orderline: orderline }));
        this.store.dispatch(calculateTotalPrice());
        alert('La tisane est ajoutée au panier.');
      } else {
        this.message = "Rupture de stock"
        console.log("La quantité n'est pas diponible");
      }
    }
  }
}
