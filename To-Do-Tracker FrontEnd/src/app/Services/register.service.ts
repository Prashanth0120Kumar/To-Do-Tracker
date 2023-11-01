import { Injectable } from '@angular/core';
import { USER } from '../ModelClass/User'
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private HTTP:HttpClient,) { }
  registerUrl="http://localhost:9000/api/v2/register"
  
  registerUser(user:USER):Observable<USER>{
    return this.HTTP.post<USER>(this.registerUrl,user);
  }
}
