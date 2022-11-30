import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  showAboutUs(){
    console.log('Redirect to an about us page');
  }

  showReadme(){
    this.router.navigateByUrl('faq');
  }
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

}
