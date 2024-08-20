import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, model } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from '../constants';
import { Response } from '../model/Response.model';
import { PageRequest } from '../model/page-request.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) { }

  save(data: any, model: string): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${apiUrl}/${model}/save`, data, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  saveClassAttendance(data: any[], model: string): Observable<Response<any>> {
    return this.http.post<Response<any>>(`${apiUrl}/${model}/class-attendance`, data, {
      headers: {
        'Content-Type': 'application/json'
      }
    });
  }

  delete(id: number, model: string): Observable<Response<any>> {
    return this.http.delete<Response<any>>(`${apiUrl}/${model}/delete/${id}`);
  }

  findById(id: number, model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/${model}/find/${id}`);
  }

  findAll(model: string): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/${model}`);
  }

  findInPage(model: string, pageRequest: PageRequest): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/${model}/${pageRequest.getUrlPathString()}`);
  }

 
   // Methods for attendance
 
   getStudentsByClassAndDepartment(classId: number, departmentId: number): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/student/find_students/${classId}/${departmentId}`);
  }
 

 







  //payment
  createPaymentIntent(paymentInfo: any): Observable<any> {
    return this.http.post<Response<any>>(`${apiUrl}/${model}/create-payment-intent`, paymentInfo);
  }
  getPaymentHistory(studentId: string): Observable<any> {
    return this.http.get<Response<any>>(`${apiUrl}/fees/history/${studentId}`);
  }

  getAllFees(): Observable<any> {
    return this.http.get<Response<any>>(`${apiUrl}/fees`);
  }























   // New methods to fetch classes, departments, subjects, and roles
   getClasses(): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/classes`);
  }

  getDepartments(): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/departments`);
  }

  getSubjects(): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/subjects`);
  }

  getRoles(): Observable<Response<any>> {
    return this.http.get<Response<any>>(`${apiUrl}/roles`);
  }


  getAll(endpoint: string, filters?: { className?: string, departmentName?: string }): Observable<any> {
    let params = new HttpParams();
    if (filters) {
      if (filters.className) {
        params = params.set('className', filters.className);
      }
      if (filters.departmentName) {
        params = params.set('departmentName', filters.departmentName);
      }
    }
    return this.http.get<Response<any>>(`${apiUrl}/${endpoint}`, { params });
    
  }
}



