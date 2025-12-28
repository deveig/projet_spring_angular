import { Product } from "../product/product";
import { Command } from "./command";

export interface Orderline {
    id?: number;
    product: Product;
    quantity: number;
    price: number;
    command?: Command;
}
