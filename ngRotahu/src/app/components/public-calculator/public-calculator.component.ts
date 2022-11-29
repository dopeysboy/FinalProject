import { Component, OnInit } from '@angular/core';
import { CalculatorService } from 'src/app/services/calculator.service';

@Component({
  selector: 'app-public-calculator',
  templateUrl: './public-calculator.component.html',
  styleUrls: ['./public-calculator.component.css']
})
export class PublicCalculatorComponent implements OnInit {

  constructor(private calcService: CalculatorService) { }

  ngOnInit(): void {
  }

}
