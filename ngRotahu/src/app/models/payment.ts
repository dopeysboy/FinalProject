import { Debt } from "./debt";

export class Payment {
  id : number | undefined;
  amount : number | undefined;
  paymentDate : string | undefined;
  comment : string | undefined;
  debt : Debt | undefined;

  constructor(id?:number, amount?:number, paymentDate?:string,
    comment?:string, debt?:Debt){
      this.id = id;
      this.amount = amount;
      this.paymentDate = paymentDate;
      this.comment = comment;
      this.debt = debt;
  }
}
