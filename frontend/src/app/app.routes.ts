import { Routes } from '@angular/router';
import { ProductsComponent } from './product/products/products';
import { ProductDetailsComponent } from './product/product-details/product-details';
import { AdminComponent } from './admin/admin';
import { ProductsListComponent } from './admin/products/products';
import { ProductEditComponent } from './admin/product-edit/product-edit';
import { ProductFormComponent } from './admin/product-form/product-form';
import { CartComponent } from './cart/cart';
import { InscriptionComponent } from './user/inscription/inscription';
import { UserComponent } from './user/user';
import { HomeComponent } from './home/home';
import { InscriptionAdminComponent } from './admin/inscription/inscription';
import { adminGuard } from './admin/admin-guard';
import { ConnexionAdminComponent } from './admin/connexion/connexion';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'product-details/:id', component: ProductDetailsComponent },
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'products', component: ProductsListComponent },
      { path: 'product-edit/:id', component: ProductEditComponent },
      { path: 'product-form', component: ProductFormComponent },
      { path: '', redirectTo: '/admin', pathMatch: 'full' },
    ], canActivate: [adminGuard]
  },
  { path: 'cart', component: CartComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'connexion', component: UserComponent },
  { path: 'inscription-admin', component: InscriptionAdminComponent },
  { path: 'connexion-admin', component: ConnexionAdminComponent },
];
