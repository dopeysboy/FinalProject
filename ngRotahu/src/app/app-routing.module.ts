import { NgModule } from '@angular/core';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { PublicCalculatorComponent } from './components/public-calculator/public-calculator.component';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { LogoutComponent } from './components/logout/logout.component';
import { LoggedInHomeComponent } from './components/logged-in-home/logged-in-home.component';
import { LoggedInCalculatorComponent } from './components/logged-in-calculator/logged-in-calculator.component';
import { FaqComponent } from './components/faq/faq.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { DebtResourcesComponent } from './components/debt-resources/debt-resources.component';
import { AdminComponent } from './components/admin/admin.component';
import { UserCreatedCrComponent } from './components/user-created-cr/user-created-cr.component';
import { CreditResourceCrudComponent } from './components/credit-resource-crud/credit-resource-crud.component';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [

  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'users', component: AdminComponent},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'loggedInHome', component: LoggedInHomeComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'publicCalculator', component: PublicCalculatorComponent},
  {path: 'loggedInCalc', component: LoggedInCalculatorComponent},
  {path: 'faq', component: FaqComponent},
  {path: 'aboutUs', component: AboutUsComponent},
  {path: 'resources', component: DebtResourcesComponent},
  {path: 'userCRList', component: UserCreatedCrComponent},
  {path: 'createResource', component: CreditResourceCrudComponent},
  {path: 'editResource/{id}', component: CreditResourceCrudComponent},
  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
