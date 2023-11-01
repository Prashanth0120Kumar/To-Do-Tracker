import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../Services/token.service';

@Component({
  selector: 'app-table-header',
  templateUrl: './table-header.component.html',
  styleUrls: ['./table-header.component.css']
})
export class TableHeaderComponent {
  constructor(private token:TokenService,private route:Router){}
  
  logOut(){
    this.token.clearToken();
    this.route.navigateByUrl("");
  }
}
