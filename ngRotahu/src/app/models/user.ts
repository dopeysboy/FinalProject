export class User {

  id : number | undefined
  username: string
  password: string
  email : string
  role : string | undefined
  enabled : boolean | undefined
  imgUrl : string | undefined
  firstName : string
  lastName : string

  constructor(id? : number, username:string='', password:string='', email:string = '', role?:string, enabled?:boolean, imgUrl?:string, firstName:string='', lastName:string=''){
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.enabled = enabled;
    this.imgUrl = imgUrl;
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
