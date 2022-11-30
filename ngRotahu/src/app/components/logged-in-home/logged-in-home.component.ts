import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { User } from 'src/app/models/user';
import { Debt } from 'src/app/models/debt';
import { DebtService } from 'src/app/services/debt.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-logged-in-home',
  templateUrl: './logged-in-home.component.html',
  styleUrls: ['./logged-in-home.component.css']
})
export class LoggedInHomeComponent implements OnInit {

  debts : Debt[] = [];

  constructor(private debtService:DebtService, private user: UserService, private auth: AuthService, private router: Router) { }



  loadDebts(){
    this.debtService.index().subscribe({
      next: (debts: Debt[])=>{
        console.log(debts);
        this.debts = debts;
      },
      error: (oops) => {
        console.error('loggedInHome: error getting debts');
        console.error(oops);
      }
    })
  }

  getDebtsList(debts: []){
    return debts.length
  }

  ngOnInit(): void {
    this.loadDebts();
  }

}
