import { createReducer, on } from '@ngrx/store';
import { CartState } from './cart.state';
import * as CartActions from './cart.action';
import { Orderline } from '../orderline';

export const initialState: CartState = {
  orderLines: [],
  totalPrice: 0,
};

export const cartReducer = createReducer(
  initialState,
  on(CartActions.addItem, (state, { orderline }) => {
    return !!state.orderLines.find((ol) => ol.product.id == orderline.product.id)
      ? {
          ...state,
          orderLines: state.orderLines.map((ol) =>
            ol.product.id == orderline.product.id
              ? {
                  ...ol,
                  quantity: ol.quantity + orderline.quantity,
                  price: ol.product.price * (ol.quantity + orderline.quantity),
                }
              : ol
          ),
        }
      : { ...state, orderLines: [...state.orderLines, orderline] };
  }),
  on(CartActions.updateItem, (state, { orderline }) => ({
    ...state,
    orderLines: state.orderLines.map((ol) =>
      ol.product.id == orderline.product.id
        ? { ...ol, quantity: orderline.quantity, price: orderline.price }
        : ol
    ),
  })),
  on(CartActions.deleteItem, (state, { id }) => ({
    ...state,
    orderLines: state.orderLines.filter((ol) => ol.product.id != id),
  })),
  on(CartActions.updateTotalPrice, (state) => ({
    ...state,
    totalPrice: state.orderLines.map((ol) => ol.price).reduce((acc, curr) => acc + curr),
  })),
  on(CartActions.clear, (state) => ({
    ...state,
    orderLines: [],
    totalPrice: 0,
  })),
  on(CartActions.calculateTotalPrice, (state) => ({
    ...state,
    totalPrice: state.orderLines.map((ol) => ol.price).reduce((acc, curr) => acc + curr),
  }))
);
