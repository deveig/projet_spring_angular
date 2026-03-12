import { Component, input, output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Product } from '../../product/product';

@Component({
  selector: 'app-generic-product-form',
  imports: [FormsModule],
  templateUrl: './generic-product-form.html',
  styleUrl: './generic-product-form.css',
})
export class GenericProductForm {
  product = input<Product>()
  buttonName = input.required<string>();
  sendProductMethod = output();
  sendImageMethod = output<string>();
  productMethod() {
    this.sendProductMethod.emit();
  }
  imageMethod(event: Event) {
    this.sendImageMethod.emit((event.target as HTMLInputElement).value);
  } 
}
