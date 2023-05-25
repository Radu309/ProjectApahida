import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Dish} from "./dish";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class DishService{
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){ }

  public getDishes(): Observable<Dish[]>{
    return this.http.get<Dish[]>(`${this.apiServerUrl}/dish/all`);
  }
  public getDishById(dishId: number): Observable<Dish>{
    return this.http.get<Dish>(`${this.apiServerUrl}/dish/find/${dishId}`);
  }
  public addDish(newDish: Dish): Observable<Dish>{
    return this.http.post<Dish>(`${this.apiServerUrl}/dish/add`,newDish);
  }
  public updateDish(newDish: Dish): Observable<Dish>{
    return this.http.put<Dish>(`${this.apiServerUrl}/dish/update`, newDish);
  }
  public deleteDish(dishId: number): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/dish/delete/${dishId}`);
  }
}
