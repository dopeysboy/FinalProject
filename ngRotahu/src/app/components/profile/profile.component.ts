import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  loggedInUser: User = new User();

  constructor(private userService: UserService, private authService: AuthService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (user) => {
        this.loggedInUser = user;
      },
      error: (problem) => {
        console.error('ProfileComponent.getUser(): Error getting User');
      }
    });
  }

  disable() {
    this.userService.disable(this.loggedInUser).subscribe({
      next: () => {
        console.log('Deleted');
      },
      error (problem) {
        console.error('ProfileComponent.disable(): Problem disabling user');
      }
    });
  }

}
