import { User } from "./user";

export class CreditResource {
  id : number | undefined;
  description : string | undefined;
  videoUrl : string | undefined;
  siteUrl : string | undefined;
  debtIntensity : number | undefined;
  createdAt : string | undefined;
  updatedAt : string | undefined;
  enabled : boolean | undefined;
  createdBy : User | undefined;

  constructor(id?:number, description?:string, videoUrl?:string, siteUrl?:string,
    debtIntensity?:number, createdAt?:string, updatedAt?:string, enabled?:boolean,
    createdBy?:User){
      this.id = id;
      this.description = description;
      this.videoUrl = videoUrl;
      this.siteUrl = siteUrl;
      this.debtIntensity = debtIntensity;
      this.createdAt = createdAt;
      this.createdBy = createdBy;
      this.updatedAt = updatedAt;
      this.enabled = enabled;
    }
}
