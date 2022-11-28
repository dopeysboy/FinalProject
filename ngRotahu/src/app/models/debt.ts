import { DebtLender } from "./debt-lender";
import { DebtType } from "./debt-type";
import { Payment } from "./payment";

export class Debt {
  id : number | undefined;
  name : string | undefined;
  monthlyInterestRate : number | undefined;
  minimumMonthlyPayment : number | undefined;
  initialBalance : number | undefined;
  currentBalance : number | undefined;
  priority : number | undefined;
  debtType : DebtType | undefined;
  debtLender : DebtLender | undefined;
  payments : Payment[] | undefined;
  createdAt : string | undefined;

  constructor(id?:number, name?:string, monthlyInterestRate?:number,
    minimumMonthlyPayment?:number, initialBalance?:number, currentBalance?:number,
    priority?:number, debtType?:DebtType, debtLender?:DebtLender, payments?:Payment[],
    createdAt?:string){
      this.id = id;
      this.name = name;
      this.monthlyInterestRate = monthlyInterestRate;
      this.minimumMonthlyPayment = minimumMonthlyPayment;
      this.initialBalance = initialBalance;
      this.currentBalance = currentBalance;
      this.priority = priority;
      this.debtType = debtType;
      this.debtLender = debtLender;
      this.payments = payments;
      this.createdAt = createdAt;
  }
}
