import { Component, OnInit } from '@angular/core';
import { CreditResource } from 'src/app/models/credit-resource';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CreditResourceService } from 'src/app/services/credit-resource.service';
import { FitnessService } from 'src/app/services/fitness.service';
import { IncomeService } from 'src/app/services/income.service';

@Component({
  selector: 'app-debt-resources',
  templateUrl: './debt-resources.component.html',
  styleUrls: ['./debt-resources.component.css']
})
export class DebtResourcesComponent implements OnInit {

  fitnessScore : number = 0;
  allCR : CreditResource[] = [];
  goodCR : CreditResource[] = [];
  badCR : CreditResource[] = [];

  shownCR: CreditResource[] = [];

  constructor(private fitness : FitnessService, private crServ: CreditResourceService) { }

  reloadCards(){
    if(this.fitnessScore <= 40){
      //select three random bad
    } else if(this.fitnessScore >= 60){
      //select three random good
    } else if(this.fitnessScore <= 50){
      //select two bad one good
    } else {
      //select two good one bad
    }
  }
  sortCR(crs : CreditResource[]){
    for(let cr of crs){
      if(cr.debtIntensity && cr.debtIntensity < 50){
        this.badCR.push(cr);
      } else {
        this.goodCR.push(cr);
      }
    }

  }

  retrieveCR(){
    this.crServ.index().subscribe({
      next: (results) => {
        this.allCR = results;
        this.sortCR(this.allCR);
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  calculateFitness(){
    this.fitness.calculateUserFitness().subscribe({
      next: (fitnessScore )=>{
        this.fitnessScore = fitnessScore;
        this.retrieveCR();
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
