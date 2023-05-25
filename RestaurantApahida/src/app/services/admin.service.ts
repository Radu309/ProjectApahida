import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Admin} from "./admin";

@Injectable({providedIn: 'root'})
export class AdminService{
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){ }

  public authenticateAdmin(newAdmin: Admin): Observable<Boolean>{
    return this.http.post<Boolean>(`${this.apiServerUrl}/admin/authenticate`, newAdmin);
  }
  public getAdmins(): Observable<Admin[]>{
    return this.http.get<Admin[]>(`${this.apiServerUrl}/admin/all`);
  }
  public getAdminById(adminId: number): Observable<Admin>{
    return this.http.get<Admin>(`${this.apiServerUrl}/admin/find/${adminId}`);
  }
  public addAdmin(newAdmin: Admin): Observable<Admin>{
    return this.http.post<Admin>(`${this.apiServerUrl}/admin/add`,newAdmin);
  }
  public updateAdmin(newAdmin: Admin): Observable<Admin>{
    return this.http.put<Admin>(`${this.apiServerUrl}/admin/update`, newAdmin);
  }
  public deleteAdmin(adminId: Admin): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/admin/delete/${adminId}`);
  }
}
