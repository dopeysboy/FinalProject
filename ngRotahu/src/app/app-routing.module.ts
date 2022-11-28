import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PublicCalculatorComponent } from './components/public-calculator/public-calculator.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [

  // {path: '', pathMatch: 'full', redirectTo: 'home'}
  // {path: 'home', component: 'home'},
  // {path: 'login', component: 'login'},
  // {path: 'profile', component: 'profile'},
  // {path: 'publicCalculator', component: 'publicCalculator'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
