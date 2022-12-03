import { Component, OnInit, ViewChild } from '@angular/core';
import { Chart, ChartConfiguration, ChartData, ChartType, Colors } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { Debt } from 'src/app/models/debt';
import { Expense } from 'src/app/models/expense';
import { Income } from 'src/app/models/income';
import { DebtService } from 'src/app/services/debt.service';
import { ExpenseService } from 'src/app/services/expense.service';
import { IncomeService } from 'src/app/services/income.service';

@Component({
  selector: 'app-user-pie-chart',
  templateUrl: './user-pie-chart.component.html',
  styleUrls: ['./user-pie-chart.component.css']
})
export class UserPieChartComponent implements OnInit {

  expenses: Expense[] = [];
  incomes: Income[] = [];
  debts: Debt[] = [];


  updateChartIncomes(incomes: any){
    let incomeTotal : number = 0;
   // let color : string = 'rgba(0, 255, 0, 1)';

    for(let income of this.incomes){
      if(income.amount){
        if(income?.frequency?.name === 'weekly'){
          incomeTotal += income.amount * 4;
        } else if (income?.frequency?.name === 'quarterly'){
          incomeTotal += income.amount / 4;
        } else {
          incomeTotal += income.amount;
        }
      }
    }
    this.pieChartData.datasets[0].data.push(incomeTotal);

  //  this.pieChartData.datasets[0].backgroundColor = color;
    this.pieChartData.labels?.push("Income");
    this.chart?.update();
  }
  updateChartExpenses(expenses: any){
    let expenseTotal : number = 0;
   // let color : string = 'rgba(0,0,255,1)';

    for(let expense of this.expenses){
      if(expense.amount){
        if(expense?.frequency?.name === 'weekly'){
          expenseTotal += expense.amount * 4;
        } else if(expense?.frequency?.name === 'quarterly'){
          expenseTotal += expense.amount / 4;
        } else{
          expenseTotal += expense.amount;
        }
      }
    }
    this.pieChartData.datasets[0].data.push(expenseTotal);
    //this.pieChartData.datasets[1].backgroundColor = color;
    this.pieChartData.labels?.push("Expense");
    this.chart?.update();
  }
  updateChartDebts(debts: any){
    let debtTotal : number = 0;
   // let color: string = 'rgba(255, 0, 0 ,1)';

    for(let debt of this.debts){
      if(debt.minimumMonthlyPayment){
        debtTotal += debt.minimumMonthlyPayment;
      }
    }
    this.pieChartData.datasets[0].data.push(debtTotal);
    //this.pieChartData.datasets[2].backgroundColor = color;
    //this.pieChartData.datasets[2].backgroundColor
    this.pieChartData.labels?.push("Debt");
    this.chart?.update();
  }
  constructor(private debtServ: DebtService, private expenseServe: ExpenseService, private incomeServ: IncomeService) { }

  ngOnInit(): void {
    Chart.register(Colors);
    //load expenses
    this.debtServ.index().subscribe({
      next: (results) => {
        this.debts = results;
        this.updateChartDebts(results);
        console.log(this.debts);
      },
      error: (err) => {
        console.error(err);
      }
    });
    //load incomes
    this.incomeServ.index().subscribe({
      next: (results) => {
        this.incomes = results;
        console.log(this.incomes);
        this.updateChartIncomes(results);
      },
      error: (err) => {
        console.error(err);
      }
    });
    //load debts
    this.expenseServe.index().subscribe({
      next: (results) => {
        this.expenses = results;
        console.log(this.expenses);
        this.updateChartExpenses(results);
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

  public pieChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: 'right',
        labels: {
          color: 'rgba(255,255,255,1)'
        }
      },
    }
  };

  public pieChartData: ChartData<'pie', number[], string | string[]> = {
    labels: [],
    datasets: [ {
      data: [],
      backgroundColor: ['red', 'green', 'blue']
    }]
  };

  public pieChartType: ChartType = 'pie';
}
