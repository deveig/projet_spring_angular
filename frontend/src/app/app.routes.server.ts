import { RenderMode, ServerRoute } from '@angular/ssr';

export const serverRoutes: ServerRoute[] = [
  {
    path: '',
    renderMode: RenderMode.Server,
  },
  {
    path: 'products',
    renderMode: RenderMode.Server,
  },
  {
    path: 'product-details/:id',
    renderMode: RenderMode.Server,
  },
  {
    path: 'admin',
    renderMode: RenderMode.Server,
  },
  {
    path: 'admin/products',
    renderMode: RenderMode.Server,
  },
  {
    path: 'admin/product-edit/:id',
    renderMode: RenderMode.Server,
  },
  {
    path: 'admin/product-form',
    renderMode: RenderMode.Server,
  },
  {
    path: 'cart',
    renderMode: RenderMode.Server,
  },
  {
    path: 'inscription',
    renderMode: RenderMode.Server,
  },
  {
    path: 'connexion',
    renderMode: RenderMode.Server,
  },
  {
    path: 'inscription-admin',
    renderMode: RenderMode.Server,
  },
  {
    path: 'connexion-admin',
    renderMode: RenderMode.Server,
  },
];