import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../../product/product';
import { ProductService } from '../../product/product-service';
import { Router } from '@angular/router';
import { GenericProductForm } from '../generic-product-form/generic-product-form';

@Component({
  selector: 'app-product-form',
  imports: [FormsModule, GenericProductForm],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css',
})
export class ProductFormComponent {
  product = signal<Product>({
    id: 0,
    name: '',
    description: '',
    image: '',
    price: 0.0,
    quantity: 0,
  });
  buttonName = 'Enregistrer le produit';
  protected message = signal<string>('');
  productService = inject(ProductService);
  router = inject(Router);
  add() {
    this.product().image = this.product().image.replace('C:\\fakepath\\', '').split('.')[0];
    this.productService.save(this.product()).subscribe({
      complete: () => this.router.navigateByUrl('/admin/products'),
      error: (err) => {
        console.log(err);
        this.message.set('Le produit a pu être sauvegardé');
      },
    });
  }
}
