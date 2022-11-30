import { Component, OnInit, ViewChild } from '@angular/core';
import { Chart, ChartConfiguration, ChartEvent, ChartType } from 'chart.js';
import { CalculatorService } from 'src/app/services/calculator.service';
import { BaseChartDirective } from 'ng2-charts';
import { Debt } from 'src/app/models/debt';

@Component({
  selector: 'app-public-calculator',
  templateUrl: './public-calculator.component.html',
  styleUrls: ['./public-calculator.component.css']
})
export class PublicCalculatorComponent implements OnInit {

  debt : Debt = new Debt();
  totalInterest : number = 0;

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [],
        label: 'Debt Name',
        backgroundColor: 'rgba(255,0,0,0.2)',
        borderColor: 'rgba(148,159,177,1)',
        pointBackgroundColor: 'rgba(148,159,177,1)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgba(148,159,177,0.8)',
        fill: 'origin'
      }
    ],
    labels: []
  }

  public lineChartOptions: ChartConfiguration['options'] = {
    elements: {
      line: {
        tension: .3
      }
    },
    scales: {
      x: {
        grid:
        {
          color: 'rgba(255, 255, 255, 1)'
        },
        ticks:
        {
          color: 'rgba(255, 255, 255, 1)'
        }
      },
      y:
      {
        position: 'left',
        grid:
        {
          color: 'rgba(255, 255, 255, 1)'
        },
        ticks:
        {
          color: 'rgba(255, 255, 255, 1)'
        }
      },
    },

    plugins:
    {
      legend: {
        display: true,
        labels: {
          color: 'rgba(255, 255, 255, 1)'
        }
      },
    }
  }

  public lineChartType: ChartType = 'line';

  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  chartHovered(event: any){
   //console.log(event);
  }

  chartClicked(event: any){
    //console.log(event);
  }

  formSubmit(debt: Debt){
    this.calcService.freeCalc(debt).subscribe({
      next: (results) =>{
        this.refreshChart(results, debt);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  refreshChart(data: any, debt: Debt): void{
    let dataArr : number[] = [];
    let monthsArr : number[] = [];

    for(let i = 1; i < 60; i++){
      if(data[i]){
        if(data[i] < 0){
          dataArr.push(0);
        } else{
          dataArr.push(data[i]);
        }
        monthsArr.push(i);
      }
    }
    this.lineChartData.datasets[0].data = dataArr;
    this.lineChartData.labels = monthsArr;
    this.lineChartData.datasets[0].label = debt.name;
    this.chart?.update();

    this.totalInterest = data[-1];
  }

  constructor(private calcService: CalculatorService) {
    Chart.register();
  }

  ngOnInit(): void {
    this.debt.name = "Input your debt";
    this.debt.currentBalance = 12323;
    this.debt.minimumMonthlyPayment = 600;
    this.debt.annualPercentageRate = 12;

    this.calcService.freeCalc(this.debt).subscribe({
      next: (results) => {
        this.refreshChart(results, this.debt);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

}
