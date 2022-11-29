import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Debt } from 'src/app/models/debt';
import { DebtService } from 'src/app/services/debt.service';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logged-in-home',
  templateUrl: './logged-in-home.component.html',
  styleUrls: ['./logged-in-home.component.css']
})
export class LoggedInHomeComponent implements OnInit {

  debts : Debt[] = [];

  constructor(private debtService:DebtService, private router : Router, private auth: AuthService) { }



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



  deleteDebt(debtId = 0){
    this.debtService.destroy(debtId).subscribe({
      next: (success)=>{
        this.loadDebts();
      },
      error: (err)=> {
        console.error('loggedinHome.deleteDebt: could not delete');
        console.error(err);
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
