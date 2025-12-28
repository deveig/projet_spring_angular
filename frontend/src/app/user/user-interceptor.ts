import { HttpInterceptorFn } from '@angular/common/http';

export const userInterceptor: HttpInterceptorFn = (req, next) => {
  // Jeton après authentification pour autorisation vers les requêtes produits ...
  const token = localStorage.getItem('token');
  if (token) {
    const cloned = req.clone({
      setHeaders: { Authorization: `Bearer ${JSON.parse(localStorage.getItem('token')!).token}` },
    });
    return next(cloned);
  }
  return next(req);
};
