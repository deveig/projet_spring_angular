import { Component, inject } from '@angular/core';
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
    lastname: '',
    firstname: '',
    address: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
  };
  userService = inject(UserService);
  router = inject(Router);
  inscrire() {
    this.userService.save(this.user).subscribe({
      complete: () => this.router.navigateByUrl('/connexion'),
      error: (error) => console.log(error),
    });
  }
}
