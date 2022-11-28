import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { PublicCalulatorComponent } from './public-calulator/public-calulator.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { UserDebtComponent } from './user-debt/user-debt.component';
import { ProfileComponent } from './profile/profile.component';
import { CompanyDetailComponent } from './company-detail/company-detail.component';
import { AdviceComponent } from './advice/advice.component';
import { RatingComponent } from './models/rating/rating.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PublicCalculatorComponent } from './components/public-calculator/public-calculator.component';
import { UserDebtComponent } from './components/user-debt/user-debt.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    PublicCalculatorComponent,
    UserDebtComponent,
    NavbarComponent,
    ProfileComponent,
    CompanyDetailComponent,
    AdviceComponent,
    RatingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
