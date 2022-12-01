import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Debt } from 'src/app/models/debt';
import { DebtService } from 'src/app/services/debt.service';
import { AuthService } from 'src/app/services/auth.service';
import { DebtLenderService } from 'src/app/services/debt-lender.service';
import { DebtLender } from 'src/app/models/debt-lender';
import { DebtType } from 'src/app/models/debt-type';
import { DebtTypeService } from 'src/app/services/debt-type.service';
import { LoggedInCalculatorComponent } from '../logged-in-calculator/logged-in-calculator.component';

@Component({
  selector: 'app-logged-in-home',
  templateUrl: './logged-in-home.component.html',
  styleUrls: ['./logged-in-home.component.css']
})
export class LoggedInHomeComponent implements OnInit {
  @ViewChild(LoggedInCalculatorComponent) calculator: any;

  debts : Debt[] = [];
  lenders : DebtLender[] = [];
  types : DebtType[] = [];
  newDebt: Debt = new Debt();
  newDebtLender: DebtLender = new DebtLender();
  newDebtType: DebtType = new DebtType();

  //TODO CHANGE ME FIX ME
  //THIS NEEDS TO BE FIXED
  residualIncome : number = 10000;

  editDebt: Debt | null = null;

  public showDetails:boolean = true;
  public showForm: boolean = false;
  public buttonDetails:string = 'show';
  public buttonForm:string = 'hide';
  public showAddDebtForm:boolean = false;

  selected: null | Debt = null;

  constructor(private type : DebtTypeService, private lender: DebtLenderService, private debtService:DebtService, private router : Router, private auth: AuthService) { }

  updateNewDebtPrio(debtType: DebtType){
    for(let dt of this.types){
      if(debtType.id == dt.id){
        this.newDebt.priority = dt.defaultPriority;
      }
    }
  }

  loadDebts(){
    this.debtService.index().subscribe({
      next: (debts: Debt[])=>{
        this.debts = debts;
      },
      error: (oops) => {
        console.error('loggedInHome: error getting debts');
        console.error(oops);
      }
    })
  }

  loadTypes(){
    this.type.index().subscribe({
      next: (types: DebtType[])=>{
        this.types = types;
      },
      error: (err) => {
        console.error('loggedInHome: error getting debt types');
        console.error(err);
      }
    })
  }

  loadLenders(){
    this.lender.index().subscribe({
      next: (lenders: DebtLender[])=>{
        this.lenders = lenders;
      },
      error: (oops) => {
        console.error('loggedInHome: error getting debts');
        console.error(oops);
      }
    })
  }

  createDebt(debt: Debt, debtLender : DebtLender, debtType : DebtType){

    debt.debtLender = debtLender;
    debt.debtType = debtType;

    this.debtService.create(debt).subscribe({
      next: (createdDebt) => {
        this.loadDebts();
        this.calculator.reloadChartData(this.residualIncome);
          },
          error: (problem) => {
            console.error('LoggedInHomeComponenet.create(): Error creating new debt:');
            console.error(problem);
          }
        });
  }



  deleteDebt(debtId = 0){
    this.debtService.destroy(debtId).subscribe({
      next: (success)=>{
        this.loadDebts();
        this.calculator.reloadChartData(this.residualIncome);
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

  toggle() {
    this.showDetails = !this.showDetails;
    this.showForm = !this.showForm

    // Change the name of the button.
    if(this.showDetails){
      this.buttonDetails = "showDetails";
      this.buttonForm = "hideForm"
    }
    else{
      this.buttonDetails = "hideDetails";
      this.buttonForm = "showForm"
    }

  }

  showAddDebt(){
    this.showAddDebtForm = !this.showAddDebtForm;

  }
  hideAddDebt(){
    this.showAddDebtForm = !this.showAddDebtForm;
  }

  setEditDebt(debt: Debt) : void{
    this.editDebt = Object.assign({}, debt)
  }

  updateDebt(debt : Debt){
    this.debtService.update(debt).subscribe({
      next: (debt) => {
        this.loadDebts();
        this.calculator.reloadChartData(this.residualIncome);
      },
      error: (err) =>{
        console.log('LoggedInHomeComponent.updateDebt(); Problem updating Debt');

      }
    })
  }


  ngOnInit(): void {
    this.loadDebts();
    this.loadLenders();
    this.loadTypes();
  }


}
