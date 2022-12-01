import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChartConfiguration, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CalculatorService } from 'src/app/services/calculator.service';

@Component({
  selector: 'app-logged-in-calculator',
  templateUrl: './logged-in-calculator.component.html',
  styleUrls: ['./logged-in-calculator.component.css']
})
export class LoggedInCalculatorComponent implements OnInit {

  generateDataSets(debtPaymentPlan: any) {
    let fields : string[] = Object.keys(debtPaymentPlan);

    let monthNums : number[] = [];
    let datasetObjs = [];

    for(let field of fields){
      let obj = {
        data: [1],
        label: '',
        backgroundColor: '',
        borderColor: '',
        pointBackgroundColor: '',
        pointBorderColor: '',
        pointHoverBackgroundColor: '',
        pointHoverBorderColor: '',
        fill: 'origin'
      };

      let dataArr = [];

      for(let i = 0; i < 60; i++){
        if(debtPaymentPlan[field][i]){
          if(debtPaymentPlan[field][i] > 0){
            dataArr.push(debtPaymentPlan[field][i]);
          } else {
            dataArr.push(0);
          }
          if(i > monthNums.length){
            monthNums.push(i);
          }
        }
      }

      obj.label = field;
      obj.data = dataArr;
      obj.backgroundColor = this.generateRandomRGBA();
      obj.borderColor = this.generateRandomRGBA();
      obj.pointBackgroundColor = this.generateRandomRGBA();
      obj.pointBorderColor = this.generateRandomHEX();
      obj.pointHoverBackgroundColor = this.generateRandomHEX();
      obj.pointHoverBorderColor = this.generateRandomRGBA();

      datasetObjs.push(obj);
    }

    this.lineChartData.datasets = datasetObjs;
    this.lineChartData.labels = monthNums;
    this.chart?.update();
  }

  generateRandomRGBA(): string{
    let r = Math.random() * 255;
    let g = Math.random() * 255;
    let b = Math.random() * 255;
    let a = .3;

    return `rgba(${r}, ${g}, ${b}, ${a})`;
  }

  generateRandomHEX(): string{
    let fst = (Math.random() * 255).toString(16);
    let snd = (Math.random() * 255).toString(16);
    let trd = (Math.random() * 255).toString(16);

    return `#${fst}${snd}${trd}`
  }

  /**
   * Assumes that the apr given is the APR for the year, not monthly
   * @param newTotal
   * @param oldTotal
   * @param apr
   * @returns the amount paid between oldTotal and newTotal
   */
  calculatePaymentAmount(newTotal: number, oldTotal: number, apr: number): number{
    let paymentAmount : number = 0;

    paymentAmount = ((oldTotal * apr) - ((12 * newTotal) + (12 * oldTotal))) / (12 + apr);

    return paymentAmount;
  }

  reloadChartData(residualIncome: number){
    this.calcService.calculateUserDebtsFromUser(residualIncome).subscribe({
      next: (results) => {
        this.generateDataSets(results);
      },
      error: (err) => {
        console.log(err);
      }
    })
  }
  constructor(private router: Router, private route: ActivatedRoute, private calcService: CalculatorService) { }

  ngOnInit(): void {
    let residualIncome : number = 10000;

    this.reloadChartData(residualIncome);
  }

  public lineChartData: ChartConfiguration['data'] = {
    datasets: [],
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
}
