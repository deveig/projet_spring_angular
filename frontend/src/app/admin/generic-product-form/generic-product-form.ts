import { Component, Input, input, model, OnInit, output } from '@angular/core';
import { Product } from '../../product/product';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-generic-product-form',
  imports: [FormsModule],
  templateUrl: './generic-product-form.html',
  styleUrl: './generic-product-form.css',
})
export class GenericProductForm {
  product = input<Product>() 
  buttonName = input.required<string>();
  sendMethod = output();
  methodProduct() {
    this.sendMethod.emit();
  }

}
