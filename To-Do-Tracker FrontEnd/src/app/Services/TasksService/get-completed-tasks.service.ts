import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TASK } from 'src/app/ModelClass/Task';
import { TokenService } from '../token.service';

@Injectable({
  providedIn: 'root'
})
export class GetCompletedTasksService {

  constructor(private HTTP:HttpClient,private jwtToken:TokenService) {}
  getCompletedTasksUrl:string= "http://localhost:9000/api/v2/user/completed";
  getTasks():Observable<TASK[]>
  {
    const token = this.jwtToken.getheader();
    const a = {headers:token}
    return this.HTTP.get<TASK[]>(`${this.getCompletedTasksUrl}`,a);
  }
  
}
