import { createFeatureSelector, createSelector } from "@ngrx/store";
import { CartState } from "./cart.state";

export const selectCartState = createFeatureSelector<CartState>('cart');
export const selectCart = createSelector(
  selectCartState,
  (state) => state.orderLines
);
export const selectTotalPrice = createSelector(selectCartState, (state) => state.totalPrice)
