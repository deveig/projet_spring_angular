import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = (route, state) => {
  const user = localStorage.getItem('admin');
  const router = inject(Router);
  if (user) {
    // si user est différent de null, on autorise l'accès à la route demandée
    return true;
  }
  // ici l'utilisateur n'est pas authentifié
  return router.createUrlTree(['/connexion-admin']);
};
