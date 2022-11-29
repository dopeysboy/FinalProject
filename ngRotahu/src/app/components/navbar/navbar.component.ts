import { Component, OnInit } from '@angular/core';

import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  constructor(private auth : AuthService) { }

  ngOnInit(): void {
  }

  loggedIn(): boolean{
    return this.auth.checkLogin();
  }

}
