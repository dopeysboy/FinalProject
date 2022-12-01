import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { FitnessService } from 'src/app/services/fitness.service';
import { IncomeService } from 'src/app/services/income.service';

@Component({
  selector: 'app-debt-resources',
  templateUrl: './debt-resources.component.html',
  styleUrls: ['./debt-resources.component.css']
})
export class DebtResourcesComponent implements OnInit {

  // need to get the User to calculate their financials (incomes, expenses, debts)
 fitnessScore : number = 80;


  constructor(private fitness : FitnessService, private auth : AuthService) { }

  calculateFitness(){
    this.fitness.calculateUserFitness().subscribe({
      next: (fitnessScore )=>{
        this.fitnessScore = fitnessScore;
        console.log(this.fitnessScore);
        console.log("fitness score");

      },
      error: (oops) => {
        console.error('debtResources: error getting fitness score');
        console.error(oops);
      }
    })
  }

  ngOnInit(): void {
    this.calculateFitness();
  }

}
