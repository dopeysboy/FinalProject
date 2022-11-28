import { Debt } from "./debt";

export class DebtType {
  id : number | undefined;
  description : string | undefined;
  defaultPriority : number | undefined;
  debts : Debt[] | undefined;

  constructor(id?:number, description?:string, defaultPriority?:number, debts?:Debt[]){
    this.id = id;
    this.description = description;
    this.defaultPriority = defaultPriority;
    this.debts = debts;
  }
}
