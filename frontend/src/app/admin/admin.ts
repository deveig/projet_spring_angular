import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { UserModel } from '../user/user-model';

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
