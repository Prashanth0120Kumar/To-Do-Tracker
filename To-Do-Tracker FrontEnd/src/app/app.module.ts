import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponentComponent } from './login-component/login-component.component';
import { TodayTaskComponent } from './today-task/today-task.component';
import { MatCardModule } from '@angular/material/card';
import { TodoComponent } from './todo/todo.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { DetailedViewComponent } from './detailed-view/detailed-view.component';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import { ArchivedTasksComponent } from './archived-tasks/archived-tasks.component';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { RestofTheTasksComponent } from './restof-the-tasks/restof-the-tasks.component';
import { PastTasksComponent } from './past-tasks/past-tasks.component';
import { CompletedTasksComponent } from './completed-tasks/completed-tasks.component';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { SideBarComponent } from './side-bar/side-bar.component';
import { SideBarTomorrowComponent } from './side-bar-tomorrow/side-bar-tomorrow.component';
import { LandingViewComponent } from './landing-view/landing-view.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatPaginatorModule } from '@angular/material/paginator';
import { SideBarRestComponent } from './side-bar-rest/side-bar-rest.component';
import { TableHeaderComponent } from './table-header/table-header.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponentComponent,
    LoginComponentComponent,
    TodayTaskComponent,
    TodoComponent,
    AddTaskComponent,
    DetailedViewComponent,
    HeaderComponent,
    ArchivedTasksComponent,
    RestofTheTasksComponent,
    PastTasksComponent,
    CompletedTasksComponent,
    SideBarComponent,
    SideBarTomorrowComponent,
    LandingViewComponent,
    SideBarRestComponent,
    TableHeaderComponent
    
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    FormsModule,
    HttpClientModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatButtonModule,
    MatChipsModule,
    MatTableModule,
    MatSortModule,
    NgxMaterialTimepickerModule,
    MatGridListModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatTooltipModule,
    MatPaginatorModule,
    MatSnackBarModule
    
    
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
