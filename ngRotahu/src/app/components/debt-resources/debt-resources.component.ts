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

  imgHeight : number = 200;
  imgWidth : number = 150;

  fitnessScore : number = 0;
  allCR : CreditResource[] = [];
  goodCR : CreditResource[] = [];
  badCR : CreditResource[] = [];

  shownCR: CreditResource[] = [];

  constructor(private fitness : FitnessService, private crServ: CreditResourceService) { }

  reloadCards(){
    console.log('starting reload process');
    if(this.fitnessScore <= 40){
      //select three random bad
      while(this.shownCR.length < 3){
        console.log('3b');
        let idx = Math.floor(Math.random() * this.badCR.length);
        let cr = this.badCR[idx];
        if(!this.shownCR.includes(cr)){
          this.shownCR.push(cr);
        }
      }
    } else if(this.fitnessScore >= 60){
      //select three random good
      while(this.shownCR.length < 3){
        console.log('3g');
        let idx = Math.floor(Math.random() * this.goodCR.length);
        let cr = this.goodCR[idx];
        if(!this.shownCR.includes(cr)){
          this.shownCR.push(cr);
        }
      }
    } else if(this.fitnessScore <= 50){
      //select two bad one good
      while(this.shownCR.length < 2){
        console.log('2b1g');
        let idx = Math.floor(Math.random() * this.badCR.length);
        let cr = this.badCR[idx];
        if(!this.shownCR.includes(cr)){
          this.shownCR.push(cr);
        }
      }
      let idx = Math.floor(Math.random() * this.goodCR.length);
      let cr = this.goodCR[idx];
      this.shownCR.push(cr);
    } else {
      //select two good one bad
      while(this.shownCR.length < 2){
        console.log('2g1b');
        let idx = Math.floor(Math.random() * this.goodCR.length);
        let cr = this.goodCR[idx];
        if(!this.shownCR.includes(cr)){
          this.shownCR.push(cr);
        }
      }
      let idx = Math.floor(Math.random() * this.badCR.length);
      let cr = this.badCR[idx];
      this.shownCR.push(cr);
    }
  }

  sortCR(crs : CreditResource[]){
    for(let cr of crs){
      if((cr.debtIntensity || cr.debtIntensity === 0) && cr.debtIntensity < 50){
        this.badCR.push(cr);
      } else {
        this.goodCR.push(cr);
      }
    }
    console.log(this.goodCR);
    console.log(this.badCR);
    this.reloadCards();
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
