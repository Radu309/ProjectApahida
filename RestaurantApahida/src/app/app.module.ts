import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LogInComponent } from './log-in/log-in.component';
import {RouterLink, RouterLinkActive, RouterOutlet} from "@angular/router";
import {AppModuleRouting} from "./app.module.routing";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { AdminsComponent } from './admins/admins.component';
import { RegisterComponent } from './register/register.component';
import { OrdersComponent } from './orders/orders.component';

@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    AdminsComponent,
    RegisterComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppModuleRouting,
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
