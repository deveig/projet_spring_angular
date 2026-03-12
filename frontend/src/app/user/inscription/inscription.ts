import { Component, inject, signal } from '@angular/core';
import { UserModel } from '../user-model';
import { UserService } from '../user-service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  imports: [FormsModule],
  templateUrl: './inscription.html',
  styleUrl: './inscription.css',
})
export class InscriptionComponent {
  user: UserModel = {
    lastName: '',
    firstName: '',
    address: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
  };
  message = signal<string>('')
  userService = inject(UserService);
  router = inject(Router);
  inscrire() {
    this.userService.save(this.user).subscribe({
      complete: () => this.router.navigateByUrl('/connexion'),
      error: (error) => {
        if (error.error.error == "Duplicate Resource") {
          this.message.set(error.error.message);
        } else {
          console.log(error.error.error);
        }
      },
    });
  }
}
