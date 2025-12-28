import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { provideState, provideStore } from '@ngrx/store';
import { cartReducer } from './cart/store/cart.reducer';
import { provideHttpClient, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import { userInterceptor } from './user/user-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes, withComponentInputBinding()),
    provideStore(),
    provideState({ name: 'cart', reducer: cartReducer }),
    provideHttpClient(withInterceptorsFromDi(), withInterceptors([userInterceptor])),
  ],
};
