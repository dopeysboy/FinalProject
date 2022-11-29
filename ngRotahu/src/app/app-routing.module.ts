import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PublicCalculatorComponent } from './components/public-calculator/public-calculator.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { LogoutComponent } from './components/logout/logout.component';
import { LoggedInHomeComponent } from './components/logged-in-home/logged-in-home.component';

const routes: Routes = [

<<<<<<< HEAD
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'loggedInHome', component: LoggedInHomeComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'register', component: RegisterComponent},
=======
  {path: '', pathMatch: 'full', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  // {path: 'login', component: 'login'},
  // {path: 'profile', component: 'profile'},
>>>>>>> 6da0216d0524f4f36808eab4fe5f6a09c5b27183
  {path: 'publicCalculator', component: PublicCalculatorComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
