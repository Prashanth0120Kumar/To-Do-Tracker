import { Component, OnInit } from '@angular/core';
import { USER } from '../ModelClass/User';
import { TASK } from '../ModelClass/Task';
import { RegisterService } from '../Services/register.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {
  userForm: FormGroup; 

  constructor(private registerService: RegisterService, private fb: FormBuilder) {
    this.userForm = this.fb.group({
      userEmail: ['', [Validators.required, Validators.email]],
      userPassword: ['',[Validators.required,Validators.minLength(6),Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/),],],
      userName: ['', [Validators.required]],
    });
  }

  ngOnInit(): void { }

  register() {
    if (this.userForm.valid) {
      this.registerService.registerUser(this.userForm.value).subscribe(
        (data) => {
          console.log("Registered");
        },
        (error: HttpErrorResponse) => {
          if (error.status === 409) {
            alert("User already exists.");
          } else {
            console.error("Error registering user:", error);
          }
        }
      );
    }
  }
}
