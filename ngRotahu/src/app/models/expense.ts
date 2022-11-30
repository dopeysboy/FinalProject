import { Category } from "./category";
import { Frequency } from "./frequency";
import { User } from "./user";

export class Expense {
  id : number | undefined;
  amount : number | undefined;
  description : string | undefined;
  createdAt : string | undefined;
  updatedAt : string | undefined;
  category : Category;
  frequency : Frequency;
  user : User | undefined;

  constructor(id?:number, amount?:number, description?:string, createdAt?:string,
    updatedAt?:string, category:Category = new Category(), frequency:Frequency = new Frequency(), user?:User){
      this.id = id;
      this.amount = amount;
      this.description = description;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.category = category;
      this.frequency = frequency;
      this.user = user;
    }
}
