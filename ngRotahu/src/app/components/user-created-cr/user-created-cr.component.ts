import { Component, OnInit } from '@angular/core';
import { CreditResource } from 'src/app/models/credit-resource';
import { AuthService } from 'src/app/services/auth.service';
import { CreditResourceService } from 'src/app/services/credit-resource.service';
import { Buffer } from 'buffer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-created-cr',
  templateUrl: './user-created-cr.component.html',
  styleUrls: ['./user-created-cr.component.css']
})
export class UserCreatedCrComponent implements OnInit {

  imgHeight : number = 200;
  imgWidth : number = 150;

  userCrs : CreditResource[] = [];

  createNew(){
    this.router.navigateByUrl('createResource');
  }

  editResource(id : number){
    this.router.navigateByUrl(`editResource/${id}`);
  }

  constructor(private authService: AuthService, private crServ: CreditResourceService, private router: Router) { }

  ngOnInit(): void {
    let credentials = localStorage.getItem('credentials');
    let login = '';

    if(credentials) login = Buffer.from(credentials, 'base64').toString('binary');

    let username = login.split(':')[0];

    this.crServ.getByUsername(username).subscribe({
      next: (results) => {
        this.userCrs.push(...results);
        console.log(results);
        console.log(this.userCrs);
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

}
