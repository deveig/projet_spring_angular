import { Orderline } from "../orderline";


export interface CartState {
    orderLines: Orderline[];
    totalPrice: number;
}