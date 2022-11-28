export class Rating {
  id : number | undefined;
  rate: number | undefined;
  description: string | undefined;
  ratingDate: string | undefined;
  enabled: boolean | undefined;

  constructor(id?:number, rate?:number, description?:string,
    ratingDate?:string, enabled?:boolean){
      this.id = id;
      this.rate = rate;
      this.description = description;
      this.ratingDate = ratingDate;
      this.enabled = enabled;
  }
}
