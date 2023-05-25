import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import {AdminsComponent} from "./admins/admins.component";
import {RegisterComponent} from "./register/register.component";
import {OrdersComponent} from "./orders/orders.component";

const routes: Routes = [
  {path: '', component: LogInComponent},
  {path: 'admin', component: AdminsComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'register', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppModuleRouting { }
