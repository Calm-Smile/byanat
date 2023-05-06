import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map } from 'rxjs';



@Injectable({
  providedIn: 'root'
})


export class MyDataService {

  constructor(private http: HttpClient) {}

  public getData(): Observable<any> {
    const url1 ='https://byanat.wiremockapi.cloud/api/v3/towers';
    return this.http.get<any>(url1); 
  }
  
  
}
