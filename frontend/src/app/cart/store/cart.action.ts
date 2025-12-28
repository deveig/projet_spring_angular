import { createAction, props } from "@ngrx/store";
import { Orderline } from "../orderline";

export const addItem = createAction('[Cart] AddItem', props<{orderline: Orderline}>())
export const updateItem = createAction('[Cart] UpdateItem', props<{orderline: Orderline}>())
export const deleteItem = createAction('[Cart] DeleteItem', props<{id: number}>())
export const updateTotalPrice = createAction('[Cart] UpdateTotalPrice')
export const clear = createAction('[Cart] Clear')
export const calculateTotalPrice = createAction('[Cart] Calculate')