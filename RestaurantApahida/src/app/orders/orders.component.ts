import { Component, OnInit } from '@angular/core';
import { Dish } from '../services/dish';
import { Router } from '@angular/router';
import { DishService } from '../services/dish.service';
import { HttpErrorResponse } from '@angular/common/http';
import { OrderService } from '../services/orders.service';
import { Order } from '../services/orders';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  dishes: Dish[] = [];
  orders: Order[] = [];
  dishesForOneOrder: Dish[] = [];
  dishElement: Dish | undefined;
  totalPrice: number = 0;

  constructor(private router: Router, private dishService: DishService, private orderService: OrderService) {}

  ngOnInit() {
    this.getDishes();
    this.getOrders();
  }

  clickOnAddDish(dish: Dish) {
    if (dish.stock > 0) {
      this.dishesForOneOrder.push(dish);
      dish.stock--;
      this.updateDish(dish).then(() => {
        this.totalPrice += dish.price;
        this.getDishes();
      });
    }
  }

  async clickOnAddOrder() {
    const date: Date = new Date();
    const currentDate: string = this.formatDate(date).toString();
    const currentTime: string = this.formatTime(date).toString();
    const totalPrice: number = this.totalPrice;
    await this.getOrders();
    const size: number = this.orders.length;
    const newOrder: Order = {
      id: size + 1,
      localDate: currentDate,
      localTime: currentTime,
      status: 'New Order',
      totalPrice: totalPrice,
      dishList: [],
    };
    await this.addOrder(newOrder).then(() => {
      this.totalPrice = 0.0;
    });
    for(let dish of this.dishesForOneOrder) {
      await this.addOrderWithDish(newOrder.id, dish.id);
    }
    await this.getOrders().then(() => {
      this.dishesForOneOrder = [];
    });
  }

  onSelectAnotherStatus(order: Order, newStatus: Event) {
    console.log(order);
    order.status = (newStatus.target as HTMLInputElement).value;
    this.orderService.updateOrder(order).subscribe();
  }
  padTo2Digits(num: number) {
    return num.toString().padStart(2, '0');
  }
  formatDate(date: Date) {
    return [
      date.getFullYear(),
      this.padTo2Digits(date.getMonth() + 1),
      this.padTo2Digits(date.getDate()),
    ].join('-');
  }
  formatTime(date: Date) {
    return [
      this.padTo2Digits(date.getHours()),
      this.padTo2Digits(date.getMinutes()),
      this.padTo2Digits(date.getSeconds()),
    ].join(':');
  }

  updateDish(dish: Dish): Promise<void> {
    return new Promise<void>((resolve) => {
      this.dishService.updateDish(dish).subscribe(
        (response: Dish) => {
          resolve();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          resolve();
        }
      );
    });
  }

  addOrder(order: Order): Promise<void> {
    return new Promise<void>((resolve) => {
      this.orderService.addOrder(order).subscribe(() => {
        resolve();
      });
    });
  }
  addOrderWithDish(orderID: number, dishId: number): Promise<void> {
    return new Promise<void>((resolve) => {
          this.orderService.addOrderWithDish(orderID, dishId).subscribe(() => {
            resolve();
          });
        });
  }
  getDishes(): void {
    this.dishService.getDishes().subscribe(
      (response: Dish[]) => {
        this.dishes = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  getOrders(): Promise<void> {
    return new Promise<void>((resolve) => {
      this.orderService.getOrders().subscribe(
        (response: Order[]) => {
          this.orders = response;
          resolve();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          resolve();
        }
      );
    });
  }
  getDishById(id: number): void {
    this.dishService.getDishById(id).subscribe(
      (response: Dish) => {
        this.dishElement = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
