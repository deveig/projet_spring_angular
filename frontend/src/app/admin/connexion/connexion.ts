import { Component, inject, signal } from '@angular/core';
import { UserModel } from '../../user/user-model';
import { Router, RouterLink } from '@angular/router';
import { AdminService } from '../admin-service';
import { LoginLogoutService } from '../../user/login-logout';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-connexion',
  imports: [FormsModule, RouterLink],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class ConnexionAdminComponent {
  user: UserModel = { username: '', password: '' };
  message = signal<string>('');
  router = inject(Router);
  userService = inject(AdminService);
  logService = inject(LoginLogoutService);
  seConnecter() {
    this.userService.findByUsernameAndPassword(this.user).subscribe({
      next: (token) => {
        localStorage.setItem('admin', JSON.stringify(this.user));
        localStorage.setItem('token', JSON.stringify(token));
        this.logService.isConnected(true);
        this.router.navigateByUrl('/');
      },
      error: (error) => {
        console.log(error);
        this.message.set("Vous n'êtes pas connu de nos services, inscrivez-vous!");
      },
    });
  }
}
