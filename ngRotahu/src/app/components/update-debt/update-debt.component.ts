import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Debt } from 'src/app/models/debt';
import { DebtService } from 'src/app/services/debt.service';

@Component({
  selector: 'app-update-debt',
  templateUrl: './update-debt.component.html',
  styleUrls: ['./update-debt.component.css']
})
export class UpdateDebtComponent implements OnInit {

  constructor(private debtService : DebtService, private router: Router) { }

  ngOnInit(): void {
  }

    //probably getting moved into update-debt
    editDebt(debt : Debt): void{
      this.debtService.update(debt).subscribe({
        next: (updated) =>{
        this.router.navigateByUrl('/loggedInHome');
        },
        error: (err)=> {
          console.error('updateDebt.editDebt: could not update');
          console.error(err);
        }

      })

    }

}
