import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AdminService } from '../admin-service';
import { UserModel } from '../../user/user-model';

@Component({
  selector: 'app-inscription',
  imports: [FormsModule],
  templateUrl: './inscription.html',
  styleUrl: './inscription.css',
})
export class InscriptionAdminComponent {
  user: UserModel = {
    lastname: '',
    firstname: '',
    address: '',
    phoneNumber: '',
    email: '',
    username: '',
    password: '',
  };
  adminService = inject(AdminService);
  router = inject(Router);
  inscrire() {
    this.adminService.save(this.user).subscribe({
      complete: () => this.router.navigateByUrl('/connexion-admin'),
      error: (error) => console.log(error),
    });
  }
}
