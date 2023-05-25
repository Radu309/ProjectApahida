import { Component } from '@angular/core';
import {Admin} from "../services/admin";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {AdminService} from "../services/admin.service";
import {EmployeeService} from "../services/employee.service";
import {Employee} from "../services/employee";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {
  admins: Admin[] = [];
  employees: Employee[] = [];
  constructor(private router: Router,
              private adminService: AdminService,
              private employeeService: EmployeeService){ }

  ngOnInit() {
    this.getAdmins();
    this.getEmployees();
    localStorage.clear();
  }

  public clickLogIn(username: string, password: string): void{
    for(let admin of this.admins){
      if(admin.username == username){
        admin.password = password;
        this.adminService.authenticateAdmin(admin).subscribe(
          (response: Boolean) => {
            if(response){
              localStorage.setItem("Admin", admin.id.toString());
              this.router.navigate([`admin`]);
            }
            else
              alert("Try again");
          },
          (error:HttpErrorResponse)=> {
            alert(error.message)
          })
      }
    }
    for(let employee of this.employees){
      if(employee.username == username){
        employee.password = password;
        this.employeeService.authenticateEmployee(employee).subscribe(
          (response: Boolean) => {
            if(response){
              localStorage.setItem("Employee", employee.id.toString());
              this.router.navigate([`orders`]);
            }
            else
              alert("Try again");
          },
          (error:HttpErrorResponse)=> {
            alert(error.message)
          })
      }
    }
  }

  public getAdmins():void{
    this.adminService.getAdmins().subscribe(
      (response:Admin[]) => {
        this.admins = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public getEmployees():void{
    this.employeeService.getEmployees().subscribe(
      (response:Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
