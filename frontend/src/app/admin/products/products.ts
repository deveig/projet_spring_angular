import { Component, inject, signal } from '@angular/core';
import { ProductService } from '../../product/product-service';
import { Product } from '../../product/product';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-products',
  imports: [FormsModule, RouterLink],
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class ProductsListComponent {
  productService = inject(ProductService);
  router = inject(Router);
  protected products = signal<Product[]>([]);
  protected message = signal<string>('');
  ngOnInit(): void {
    this.productService.findAll().subscribe({
      next: (value) => this.products.set(value),
      error: (err) => {
        console.log(err);
        this.message.set('Produits non disponibles');
      },
    });
  }
  delete(index: number) {
    const id = this.products().at(index)!.id;
    this.productService.delete(id).subscribe({
      complete: () => {
        this.message.set('Le produit est supprimé');
        this.products().splice(index, 1);
      },
      error: (err) => {
        console.log(err);
        this.message.set("Le produit n'a pas pu être supprimé");
      },
    });
  }
}
