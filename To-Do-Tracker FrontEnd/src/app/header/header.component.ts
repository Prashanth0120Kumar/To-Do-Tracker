import { Component } from '@angular/core';
import { TokenService } from '../Services/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private token:TokenService,
    private route:Router){}
    logOut(){
    this.token.clearToken();
    this.route.navigateByUrl("");
  }

}
