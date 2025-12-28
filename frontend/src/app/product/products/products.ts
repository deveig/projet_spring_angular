import { Component, inject, OnInit, signal } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product-service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Store } from '@ngrx/store';
import { addItem, calculateTotalPrice } from '../../cart/store/cart.action';
import { Orderline } from '../../cart/orderline';
import { selectCart, selectTotalPrice } from '../../cart/store/cart.selector';

@Component({
  selector: 'app-products',
  imports: [FormsModule, RouterLink],
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class ProductsComponent implements OnInit {
  protected products = signal<Product[]>([]);
  protected criterias = ['Menthe', 'Verveine', 'Citron', 'Thym', 'Camomille'];
  protected message = signal<string>('');
  productService = inject(ProductService);
  router = inject(Router);
  store = inject(Store);
  ngOnInit(): void {
    this.productService.findAll().subscribe({
      next: (value) => this.products.set(value),
      error: (err) => console.log(err),
    });
  }
  addToCart(index: number) {

        if (this.products().at(index)!.quantity >= 1) {
          const orderline: Orderline = {product: this.products().at(index)!, quantity: 1, price: this.products().at(index)!.price};
          this.store.dispatch(addItem({ orderline: orderline }));
          this.store.dispatch(calculateTotalPrice())
          alert("La tisane est ajoutée au panier.")
        } else {
          this.message.set('Rupture de stock');
        }
      }
  
  selectByCriteria(event: Event) {
    const criteria = (event.target as HTMLSelectElement).value;
    if (this.criterias.some((c) => c == criteria)) {
      this.productService.findByCriteria(this.criterias.filter((c) => c == criteria)[0]).subscribe({
        next: (value) => this.products.set(value),
        error: (err) => {
          console.log(err);
          this.message.set('Produits non disponibles');
        },
      });
    } else {
      this.productService.findAll().subscribe({
        next: (value) => this.products.set(value),
        error: (err) => {
          console.log(err);
          this.message.set('Produits non disponibles');
        },
      });
    }
  }
}
