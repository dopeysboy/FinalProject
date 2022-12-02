import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isAdmin: boolean = false;

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getIsAdmin();
  }

  loggedIn(): boolean{
    return this.auth.checkLogin();
  }

  logout(){
    this.auth.logout();
    this.router.navigateByUrl('/home');
  }

  getIsAdmin():boolean {
    return this.auth.isAdmin();
  }

}
