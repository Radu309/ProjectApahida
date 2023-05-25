import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee";

@Injectable({providedIn: 'root'})
export class EmployeeService{
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){ }

  public authenticateEmployee(newEmployee: Employee): Observable<Boolean>{
    return this.http.post<Boolean>(`${this.apiServerUrl}/employee/authenticate`, newEmployee);
  }
  public getEmployees(): Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);
  }
  public getEmployeeById(employeeId: number): Observable<Employee>{
    return this.http.get<Employee>(`${this.apiServerUrl}/employee/find/${employeeId}`);
  }
  public addEmployee(newEmployee: Employee): Observable<Employee>{
    return this.http.post<Employee>(`${this.apiServerUrl}/employee/add`,newEmployee);
  }
  public updateEmployee(newEmployee: Employee): Observable<Employee>{
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update`, newEmployee);
  }
  public deleteEmployee(employeeId: Employee): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${employeeId}`);
  }
}
