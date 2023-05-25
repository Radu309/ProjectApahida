import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Order} from "./orders";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {DataRequest} from "./dataRequest";

@Injectable({providedIn: 'root'})
export class OrderService{
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){ }

  public getOrders(): Observable<Order[]>{
    return this.http.get<Order[]>(`${this.apiServerUrl}/order/all`);
  }
  public getOrderById(orderId: number): Observable<Order>{
    return this.http.get<Order>(`${this.apiServerUrl}/order/find/${orderId}`);
  }
  public addOrder(newOrder: Order): Observable<Order>{
    return this.http.post<Order>(`${this.apiServerUrl}/order/add`,newOrder);
  }
  public updateOrder(newOrder: Order): Observable<Order>{
    return this.http.put<Order>(`${this.apiServerUrl}/order/update`, newOrder);
  }
  public deleteOrder(orderId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/order/delete/${orderId}`);
  }
  public addOrderWithDish(orderId: number, dishId: number): Observable<Order>{
    return this.http.put<Order>((`${this.apiServerUrl}/order/${orderId}/dish/${dishId}`),null);
  }
  public generateReports(reportType: number, requestData: DataRequest): Observable<void> {
    return this.http.post<void>(`${this.apiServerUrl}/order/dates/${reportType}`, requestData);
  }
}
