import {Component, OnInit} from '@angular/core';
import {Admin} from "../services/admin";
import {Router} from "@angular/router";
import {AdminService} from "../services/admin.service";
import {HttpErrorResponse} from "@angular/common/http";
import {OrderService} from "../services/orders.service";
import {DishService} from "../services/dish.service";
import {Dish} from "../services/dish";
import {Order} from "../services/orders";
import {EmployeeService} from "../services/employee.service";
import {Employee} from "../services/employee";
import {DataRequest} from "../services/dataRequest";

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.css']
})

export class AdminsComponent implements OnInit{
  dishes: Dish[] = [];
  orders: Order[] = [];
  employees: Employee[] = [];
  employeeName: string = "";
  employeeAge: string = "";
  employeeUsername: string = "";
  employeePassword: string = "";
  dishName: string = "";
  dishPrice: string = "";
  dishStock: string = "";
  constructor(private router: Router,
              private adminService: AdminService,
              private orderService: OrderService,
              private dishService: DishService,
              private employeeService: EmployeeService){
    this.adminService = adminService;
    this.orderService = orderService;
    this.dishService = dishService;
    this.employeeService = employeeService;
  }

  ngOnInit() {
    this.getOrders();
    this.getDishes();
    this.getEmployees();
  }
  clickOnCreateEmployee(name: string, age: string, username: string, password: string) {
    const employee: Employee = {
      id: this.employees.length + 1,
      name: name,
      age: parseInt(age),
      username: username,
      password: password,
      employeeCode: '1'
    }
    this.employeeService.addEmployee(employee).subscribe(
      (response: Employee) => {
        this.getEmployees();
        this.employeeName = "";
        this.employeeAge = "";
        this.employeeUsername = "";
        this.employeePassword = "";
      }
    );
  }
  clickOnEditDish(name: string, price: string, stock: string) {
    let exist = 0;
    for(let dish of this.dishes){
      if(dish.name == name){
        exist = 1;
        dish.price = parseFloat(price);
        dish.stock = parseInt(stock);
        this.dishService.updateDish(dish).subscribe(
          (response: Dish) => {
            this.getDishes();
          });
      }
    }
    if(exist == 0) {
      this.getDishes();
      const newId = this.dishes[this.dishes.length - 1].id + 1;
      const newDish: Dish = {
        id:  newId,
        name: name,
        price: parseFloat(price),
        stock: parseInt(stock)
      }
      this.dishService.addDish(newDish).subscribe(
        (response: Dish) => {
          this.getDishes();
        }
      );
    }
    this.dishName = "";
    this.dishPrice = "";
    this.dishStock = "";
  }
  clickOnDeleteDish(deletedDish: Dish) {
    let exist: number = 0;
    for(let order of this.orders)
      for(let dish of order.dishList)
        if(deletedDish.id == dish.id) {
          exist = 1;
        }
    if(exist == 0)
      this.dishService.deleteDish(deletedDish.id).subscribe(
        (response: void) => {
          this.getDishes();
        }
      );
  }
  generate(type: string) {
    const startDateInput = document.getElementById('start-date') as HTMLInputElement;
    const endDateInput = document.getElementById('end-date') as HTMLInputElement;

    const startDateValue = startDateInput.value;
    const endDateValue = endDateInput.value;

    const startDate = new Date(startDateValue);
    const endDate = new Date(endDateValue);

    const startDateFormatted = startDate.toISOString().split('T')[0];
    const endDateFormatted = endDate.toISOString().split('T')[0];

    //console.log(startDateFormatted);
    //console.log(endDateFormatted);
    const date: DataRequest = {
      startDate: startDateFormatted,
      endDate: endDateFormatted
    };
    this.orderService.generateReports(parseInt(type),date).subscribe(
      () => {
        console.log("Report generated successfully");
        // Handle success
      },
      (error) => {
        console.error("Failed to generate report:", error);
        // Handle error
      }
    );
  }
  getOrders():void{this.orderService.getOrders().subscribe(
    (response : Order[]) => {
        this.orders = response;
    }
  );}
  getDishes():void{this.dishService.getDishes().subscribe(
    (response : Dish[]) => {
      this.dishes = response;
    }
  );}
  getEmployees():void{this.employeeService.getEmployees().subscribe(
    (response : Employee[]) => {
      this.employees = response;
    }
  );}
}
