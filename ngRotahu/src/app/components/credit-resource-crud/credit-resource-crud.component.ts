import { Component, OnInit } from '@angular/core';
import { CreditResource } from 'src/app/models/credit-resource';
import { CreditResourceService } from 'src/app/services/credit-resource.service';

@Component({
  selector: 'app-credit-resource-crud',
  templateUrl: './credit-resource-crud.component.html',
  styleUrls: ['./credit-resource-crud.component.css']
})
export class CreditResourceCrudComponent implements OnInit {

  newCr : CreditResource = new CreditResource();
  updateCr: CreditResource | undefined = undefined;

  submit(cr: CreditResource){
    this.crServ.create(cr).subscribe({
      next: (results) =>{
        console.log('CR was created, do something with it if you want');
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  update(cr: CreditResource, id: number){
    this.crServ.update(id, cr).subscribe({
      next: (results) => {
        console.log('CR was updated, do something with it if you want');
      },
      error: (err) => {
        console.error(err);
      }
    })
  }
  cancel(){
    this.newCr = new CreditResource();
    this.updateCr = new CreditResource();
  }

  constructor(private crServ: CreditResourceService) { }

  ngOnInit(): void {
  }

}
