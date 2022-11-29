import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PublicCalculatorComponent } from './components/public-calculator/public-calculator.component';
import { UserDebtComponent } from './components/user-debt/user-debt.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { LogoutComponent } from './components/logout/logout.component';
import { LoggedInHomeComponent } from './components/logged-in-home/logged-in-home.component';

import { NgChartsModule } from 'ng2-charts';
import { NgbAccordionModule } from '@ng-bootstrap/ng-bootstrap';
import { LoggedInCalculatorComponent } from './components/logged-in-calculator/logged-in-calculator.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    PublicCalculatorComponent,
    UserDebtComponent,
    NavbarComponent,
    ProfileComponent,
    RegisterComponent,
    LogoutComponent,
    LoggedInHomeComponent,
    LoggedInCalculatorComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgChartsModule,
    NgbAccordionModule
    ,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
