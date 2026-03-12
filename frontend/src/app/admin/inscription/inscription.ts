import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminService } from '../admin-service';
import { UserModel } from '../../user/user-model';

@Component({
  selector: 'app-inscription-admin',
  imports: [FormsModule],
  templateUrl: './inscription.html',
  styleUrl: './inscription.css',
})
export class InscriptionAdminComponent {
  user: UserModel = {
    lastName: '',
    firstName: '',
    address: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
  };
  message = signal<string>('');
  adminService = inject(AdminService);
  router = inject(Router);
  inscrire() {
    this.adminService.save(this.user).subscribe({
      complete: () => this.router.navigateByUrl('/connexion-admin'),
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
