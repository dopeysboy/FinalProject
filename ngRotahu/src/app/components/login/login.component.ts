import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser: User = new User();
  invalidLogin = false;
  errorMessage:string = '';

  constructor(private router: Router, private loginservice: AuthService) { }

  ngOnInit(): void {
  }

  login(user: User): void{
    if(user.username && user.password){
      this.loginservice.login(user.username, user.password).subscribe({
        next: (results) => {
          this.router.navigateByUrl('/loggedInHome');
        },
        error: (err) => {
          // console.error('Error in LoginComponent.login(): failed to login user');
          // console.error(err);
          this.errorMessage = 'Wrong username or password';
        }
      });
    }
  }
}
