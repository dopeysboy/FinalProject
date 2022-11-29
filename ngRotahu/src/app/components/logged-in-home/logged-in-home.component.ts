import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-logged-in-home',
  templateUrl: './logged-in-home.component.html',
  styleUrls: ['./logged-in-home.component.css']
})
export class LoggedInHomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  profile(): void {
    this.router.navigateByUrl('/profile');
  }

}
