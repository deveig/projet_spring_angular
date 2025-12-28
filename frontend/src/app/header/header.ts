import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { LoginLogoutService } from '../user/login-logout';

@Component({
  selector: 'app-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class HeaderComponent {
  isConnected = signal(!!localStorage.getItem('token'));
  router = inject(Router);
  logService = inject(LoginLogoutService);
  constructor() {
    this.logService.getSubject().subscribe((v) => this.isConnected.set(v));
  }
  login() {
    this.router.navigateByUrl('/connexion');
  }
  logout() {
    localStorage.removeItem('token');
    if(localStorage.getItem('user')){
      localStorage.removeItem('user');
    } else if(localStorage.getItem('admin')) {
      localStorage.removeItem('admin');
    }
    this.logService.isConnected(false);
    this.router.navigateByUrl('/');
  }
}
