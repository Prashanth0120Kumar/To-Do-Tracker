import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { TodayTaskComponent } from './today-task/today-task.component';
import { AuthenticationService } from './Services/authentication.service';
import { TodoComponent } from './todo/todo.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { DetailedViewComponent } from './detailed-view/detailed-view.component';
import { HeaderComponent } from './header/header.component';
import { ArchivedTasksComponent } from './archived-tasks/archived-tasks.component';
import { PastTasksComponent } from './past-tasks/past-tasks.component';
import { CompletedTasksComponent } from './completed-tasks/completed-tasks.component';
import { RestofTheTasksComponent } from './restof-the-tasks/restof-the-tasks.component';
import { LandingViewComponent } from './landing-view/landing-view.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SideBarTomorrowComponent } from './side-bar-tomorrow/side-bar-tomorrow.component';
import { SideBarRestComponent } from './side-bar-rest/side-bar-rest.component';

const routes: Routes = [
  {path:"",redirectTo:"/landing", pathMatch:"full" },
  {path:"landing",component:LandingViewComponent},
  {path:"register",component:RegisterComponentComponent},
  {path:"login",component:LoginComponentComponent},
  {path:"today",component:SideBarComponent,canActivate:[AuthenticationService]},
  {path:"todo",component:SideBarTomorrowComponent,canActivate:[AuthenticationService]},
  {path:"addTask",component:AddTaskComponent,canActivate:[AuthenticationService]},
  { path: 'task/:id', component: DetailedViewComponent ,canActivate:[AuthenticationService]},
  {path:'archived',component:ArchivedTasksComponent},
  {path:'past',component:PastTasksComponent},
  {path:'completed',component:CompletedTasksComponent},
  {path:'restOfTheTasks',component:SideBarRestComponent}
  


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
