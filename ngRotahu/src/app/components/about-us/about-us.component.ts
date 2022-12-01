import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {

  async wait(waitInMS: number){
    await new Promise( resolve => setTimeout(resolve, waitInMS));
  }

  slideTime : number = 4000;

  founder1 : HTMLElement | null = null;
  founder2 : HTMLElement | null = null;
  founder3 : HTMLElement | null = null;

  async cycleSlides(){
    if(this.founder1 && this.founder2 && this.founder3){
      while(true){
        await this.wait(this.slideTime);
        if(this.founder1?.className === 'carousel-item active'){
          this.founder1.className = 'carousel-item';
          this.founder2.className = 'carousel-item active';
        } else if(this.founder2?.className === 'carousel-item active'){
          this.founder2.className = 'carousel-item';
          this.founder3.className = 'carousel-item active';
        } else {
          this.founder3.className = 'carousel-item';
          this.founder1.className = 'carousel-item active';
        }
      }
    }
  }

  constructor() { }

  ngOnInit(): void {
    this.founder1 = document.getElementById('founder1');
    this.founder2 = document.getElementById('founder2');
    this.founder3 = document.getElementById('founder3');

    this.cycleSlides();
  }

}
