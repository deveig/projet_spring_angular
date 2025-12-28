import { Component, inject, signal } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { UserModel } from './user-model';
import { UserService } from './user-service';
import { LoginLogoutService } from './login-logout';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user',
  imports: [FormsModule, RouterLink],
  templateUrl: './user.html',
  styleUrl: './user.css',
})
export class UserComponent {
  user: UserModel = { username: '', password: '' };
  message = signal<string>("");
  router = inject(Router);
  userService = inject(UserService);
  logService = inject(LoginLogoutService);
  seConnecter() {
    this.userService.findByUsernameAndPassword(this.user).subscribe({
      next: (token) => {
        localStorage.setItem('user', JSON.stringify(this.user));
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
