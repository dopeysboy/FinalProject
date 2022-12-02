import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
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
        this.router.navigateByUrl('userCRList');
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
        this.router.navigateByUrl('userCRList');
      },
      error: (err) => {
        console.error(err);
      }
    })
  }
  cancel(){
    this.router.navigateByUrl('userCRList');
  }

  constructor(private crServ: CreditResourceService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if(this.route.snapshot.paramMap.get('id')){
      let incoming = this.route.snapshot.paramMap.get('id');
      if(incoming){
        let id = Number.parseInt(incoming);
        if(!isNaN(id)){
          this.crServ.getById(id).subscribe({
            next: (results) => {
              this.updateCr = results;
            },
            error: (err) => {
              console.log(err);
            }
          })
        }
      }
    }
  }

}
