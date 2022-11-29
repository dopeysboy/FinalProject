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

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [],
        label: 'Debt Name',
        backgroundColor: 'rgba(148,159,177,0.2)',
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
      x: {},
      'y-axis-0':
      {
        position: 'left'
      },
      // 'y-axis-1':
      // {
      //   position: 'right',
      //   grid:
      //   {
      //     color: 'rgba(255,0,0,0.3)'
      //   },
      //   ticks:
      //   {
      //     color: 'red'
      //   }
      // }
    },

    plugins:
    {
      legend: { display: true},
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
  }

  constructor(private calcService: CalculatorService) {
    Chart.register();
  }

  ngOnInit(): void {
    let debt : Debt = new Debt();
    debt.name = "Navy Federal";
    debt.currentBalance = 12323;
    debt.minimumMonthlyPayment = 600;
    debt.annualPercentageRate = 12;

    this.calcService.freeCalc(debt).subscribe({
      next: (results) => {
        this.refreshChart(results, debt);
      },
      error: (err) => {
        console.log(err);
      }
    });

  }

}
