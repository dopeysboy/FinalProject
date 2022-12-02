import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  password1: string = '';
  password2: string = '';
  errorMessage: string = '';

  newUser: User = new User();
  constructor(private auth: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.errorMessage = '';
  }

  register(user: User, password1: string, password2: string): void {
    if (password1 === password2) {
      this.errorMessage = '';
      user.password = password1;
      this.auth.register(user).subscribe({
        next: (registeredUser) => {
          this.auth.login(user.username, user.password).subscribe({
            next: (loggedInUser) => {
              this.router.navigateByUrl('/home');
            },
            error: (problem) => {
              console.error('RegisterComponent.register(): Error logging in user:');
              console.error(problem);
            }
          });
        },
        error: (fail) => {
          console.error('RegisterComponent.register(): Error registering account');
          console.error(fail);
        }
      });
    } else {
      this.errorMessage = 'Passwords do not match';
    }
  }


}
