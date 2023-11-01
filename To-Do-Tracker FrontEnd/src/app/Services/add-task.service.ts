import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TASK } from '../ModelClass/Task';
import { Observable } from 'rxjs';
import { TokenService } from './token.service'

@Injectable({
  providedIn: 'root'
})
export class AddTaskService {
  private addUrl = 'http://localhost:9000/api/v2/user/addTask';
  constructor(private http: HttpClient, private jwtToken: TokenService) {}
  addTask(task: TASK): Observable<string> {
    const token = this.jwtToken.getheader();
    const a = {headers:token};
    return this.http.post<string>(this.addUrl, task,a);
  }
}
