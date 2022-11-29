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

  constructor(private router: Router, private loginservice: AuthService) { }

  ngOnInit(): void {
  }

  checkLogin(){
    if(this.loginservice.generateBasicAuthCredentials(this.username, this.password)){
      this.router.navigate([''])
      this.invalidLogin = false
    }else{
      this.invalidLogin = true
    }
  }

}
