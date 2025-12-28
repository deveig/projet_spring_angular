import { Component, inject, OnInit, signal } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { UserModel } from '../user/user-model';
import { AdminService } from './admin-service';
import { LoginLogoutService } from '../user/login-logout';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  imports: [RouterOutlet],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class AdminComponent implements OnInit {
  user = signal<UserModel>({ username: '', password: '' });
  ngOnInit(): void {
    if (localStorage.getItem('admin')) {
      this.user.set(JSON.parse(localStorage.getItem('admin')!));
    }
  }
}
