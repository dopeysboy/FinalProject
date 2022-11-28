import { Debt } from "./debt";

export class DebtLender {
  id : number | undefined;
  name : string | undefined;
  imageUrl : string | undefined;
  siteUrl : string | undefined;
  debts : Debt[] | undefined;
  //ratings : Rating[] | undefined;

  constructor(id?:number, name?:string, imageUrl?:string, siteUrl?:string,
    debts?:Debt[]){
      this.id = id;
      this.name = name;
      this.imageUrl = imageUrl;
      this.siteUrl = siteUrl;
      this.debts = debts;
    }
}
