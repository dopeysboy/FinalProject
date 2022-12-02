import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users: User[] = [];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(): void {
    this.userService.index().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (problem) => {
        console.error('AdminComponent.getUsers(): Error getting Users');
      }
    });
  }

  toggleEnable(user: User): void {
    this.userService.toggleEnable(user).subscribe({
      next: () => {
        console.log('toggled');
        this.getUsers;
      },
      error: (problem) => {
        console.error('AdminComponent.toggleEnable(): Error toggling enabled field');
      }
    });
  }

}
