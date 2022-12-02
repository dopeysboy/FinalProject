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

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgChartsModule } from 'ng2-charts';
import { NgbAccordionModule } from '@ng-bootstrap/ng-bootstrap';
import { LoggedInCalculatorComponent } from './components/logged-in-calculator/logged-in-calculator.component';
import { UpdateDebtComponent } from './components/update-debt/update-debt.component';
import { FooterComponent } from './components/footer/footer.component';
import { FaqComponent } from './components/faq/faq.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { DrowningComponent } from './components/drowning/drowning.component';
import { DebtResourcesComponent } from './components/debt-resources/debt-resources.component';
import { ThrivingComponent } from './components/thriving/thriving.component';
import { AdminComponent } from './components/admin/admin.component';
import { CreditResourceCrudComponent } from './components/credit-resource-crud/credit-resource-crud.component';
import { UserPieChartComponent } from './components/user-pie-chart/user-pie-chart.component';
import { UserCreatedCrComponent } from './components/user-created-cr/user-created-cr.component';
import { ErrorComponent } from './components/error/error.component';
import { AdminCrListComponent } from './components/admin-cr-list/admin-cr-list.component';

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
    UpdateDebtComponent,
    FooterComponent,
    UserPieChartComponent,
    FaqComponent,
    AboutUsComponent,
    DrowningComponent,
    DebtResourcesComponent,
    ThrivingComponent,
    AdminComponent,
    CreditResourceCrudComponent,
    UserCreatedCrComponent,
    ErrorComponent,
    AdminCrListComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgChartsModule,
    NgbAccordionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
