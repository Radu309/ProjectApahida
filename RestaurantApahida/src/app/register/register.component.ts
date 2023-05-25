import { Component } from '@angular/core';
import {Admin} from "../services/admin";
import {HttpErrorResponse} from "@angular/common/http";
import {Employee} from "../services/employee";
import {Router} from "@angular/router";
import {AdminService} from "../services/admin.service";
import {EmployeeService} from "../services/employee.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  admins: Admin[] = [];
  employees: Employee[] = [];
  constructor(private router: Router,
              private adminService: AdminService,
              private employeeService: EmployeeService){ }

  ngOnInit() {
    this.getAdmins();
    this.getEmployees();
  }

  public clickOnRegisterAccount(setName: string, setAge : string, setUsername: string,
                                setPassword: string, role: string): void{

    if(role == "Admin") {
      const newAdmin: Admin = {
        id: this.admins.length + 1,
        name: setName,
        age: parseInt(setAge),
        username: setUsername,
        password: setPassword,
        adminCode: '1'
      }
      this.adminService.addAdmin(newAdmin).subscribe(
        (response: Admin) => {
          console.log(response);
          this.getAdmins();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
      this.router.navigate(['']).then(() => {
        window.location.reload();
      });
    }
    else{
      const newEmployee: Employee = {
        id: this.employees.length + 1,
        name: setName,
        age: parseInt(setAge),
        username: setUsername,
        password: setPassword,
        employeeCode: '1'
      }
      this.employeeService.addEmployee(newEmployee).subscribe(
        (response: Employee) => {
          console.log(response);
          this.getEmployees();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
      this.router.navigate(['']).then(() => {
        window.location.reload();
      });
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
