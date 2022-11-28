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

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    PublicCalulatorComponent,
    LoginComponent,
    NavbarComponent,
    UserDebtComponent,
    ProfileComponent,
    CompanyDetailComponent,
    AdviceComponent
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
