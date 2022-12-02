import { Component, OnInit } from '@angular/core';
import { CreditResource } from 'src/app/models/credit-resource';
import { CreditResourceService } from 'src/app/services/credit-resource.service';

@Component({
  selector: 'app-admin-cr-list',
  templateUrl: './admin-cr-list.component.html',
  styleUrls: ['./admin-cr-list.component.css'],
})

export class AdminCrListComponent implements OnInit {

  crs : CreditResource[] = [];

  reloadCRs(){
    this.crServ.index().subscribe({
      next: (results) => {
        console.log(results);
        this.crs = results;
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  toggleEnable(cr: CreditResource){
    this.crServ.toggleEnable(cr).subscribe({
      next: (results) => {
        this.reloadCRs();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
  constructor(private crServ: CreditResourceService) { }

  ngOnInit(): void {
    this.reloadCRs();
  }

}
