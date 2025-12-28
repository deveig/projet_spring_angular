import { Component, inject, input, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../../product/product';
import { ProductService } from '../../product/product-service';
import { ActivatedRoute, Router } from '@angular/router';
import { GenericProductForm } from '../generic-product-form/generic-product-form';

@Component({
  selector: 'app-product-edit',
  imports: [FormsModule, GenericProductForm],
  templateUrl: './product-edit.html',
  styleUrl: './product-edit.css',
})
export class ProductEditComponent implements OnInit {
  constructor() {}
  product = signal<Product>({
    id: 0,
    name: '',
    description: '',
    image: '',
    price: 0.0,
    quantity: 0,
  });
  buttonName = 'Modifier le produit';
  protected message = signal<string>('');
  productService = inject(ProductService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.productService.findById(params['id']).subscribe({
        next: (value) => this.product.set(value),
        error: (err) => {
          console.log(err);
          this.message.set("Le produit n'existe pas");
        },
      });
    });
  }
  changeProduct() {
    this.productService.update(this.product().id, this.product()).subscribe({
      complete: () => this.router.navigateByUrl('/admin/products'),
      error: (err) => {
        console.log(err);
        this.message.set("Le produit n'a pa pu être modifié");
      },
    });
  }
}
