import { Component } from '@angular/core';
import { PRIORITY, TASK } from '../ModelClass/Task';
import { TodayArchiveService } from '../Services/TasksService/ArchiveService';
import { DeleteTaskService } from '../Services/TasksService/delete-task.service';
import { TodayTasksCompletedService } from '../Services/TasksService/completionService.service';
import { Router } from '@angular/router';
import { GetrestOfTheTasksService } from '../Services/TasksService/getRest-of-the-tasks.service';
import { TodayHeadEditService } from '../Services/today-head-edit.service';
import { RestOfTheTaskByPriorityService } from '../Services/TasksService/rest-of-the-task-by-priority.service';

@Component({
  selector: 'app-restof-the-tasks',
  templateUrl: './restof-the-tasks.component.html',
  styleUrls: ['./restof-the-tasks.component.css']
})
export class RestofTheTasksComponent {
  sortAscending = true;
  showTickIcon = false;
  constructor(private service: GetrestOfTheTasksService,  
    private router: Router, 
    private priorityService: RestOfTheTaskByPriorityService,
    private editService:TodayHeadEditService,private completedTasksService:TodayTasksCompletedService
    ,private ArchiveService:TodayArchiveService,private deleteService:DeleteTaskService) { }
  Tasks: TASK[] = [];
  searchText:string=''

  message: string = '';
  ngOnInit(): void {
    this.fetchTasks();
    this.service.getTasks().subscribe(
      (data) => {
        this.Tasks = data;
        this.showTickIcon = this.Tasks.every(task => task.isCompleted);

      }
      ,(error) => {
        if (error.error && error.error.message === "You have no tasks for this day") {
          this.message = `You have no tasks for ${new Date().toLocaleDateString()} (Today)`;
        } else {
          this.message = 'An error occurred while fetching tasks.';
        }
      }
    );
 
  }



  onCardClick(taskID: number) {
    this.router.navigate(['/task', taskID]);
  }

  fetchTasks() {
    this.priorityService.getrestOfTheTaskByPriority().subscribe(
      (tasks: TASK[]) => {
        this.Tasks = tasks;
      },
      (error) => {
        console.error('Error fetching tasks:', error);
      }
    );
  }
  sortTasksByPriority() {
    const priorityOrder = [PRIORITY.HIGH, PRIORITY.MEDIUM, PRIORITY.LOW];

    this.Tasks.sort((a, b) => {
      return priorityOrder.indexOf(a.priorityLevel) - priorityOrder.indexOf(b.priorityLevel);
    });

    this.sortAscending = true; 
  }
  
  markAsCompleted(taskID: number) {
    const isComplete = true;
  
    this.completedTasksService.updateTaskCompletion(taskID, isComplete).subscribe(
      (response: any) => {
        console.log('Task marked as completed:', response);
  
        const task = this.Tasks.find(t => t.taskID === taskID);
        if (task) {
          task.isCompleted = true;
          confirm("Task Marked as Completed");
          }
      },
      (error: any) => {
        console.error('Error updating task completion', error);
      }
    );
  }
  markAsArchived(taskID: number) {
    const isArchived = true;
  
    this.ArchiveService.updateTaskArchive(taskID, isArchived).subscribe(
      (response: any) => {
        console.log('Task Archived', response);
  
        const task = this.Tasks.find(t => t.taskID === taskID);
        if (task) {
          task.isArchived = true;
          confirm("Task Moved to Archive");
          }
      },
      (error: any) => {
        console.error('Error', error);
      }
    );
  }
  deleteTask(taskID: number) {
    this.deleteService.deleteTask(taskID).subscribe(
      (response) => {
        console.log('Task deleted successfully', response);
      },
      (error) => {
        console.error('Error deleting task', error);
      }
    );
  }
  getBorderColor(priorityLevel: string): string {
    switch (priorityLevel) {
      case 'HIGH':
        return 'red';
      case 'MEDIUM':
        return 'yellow';
      case 'LOW':
        return 'blue';
      default:
        return 'transparent';
    }
  }
  onSearch() {
    this.Tasks = this.Tasks.filter((task) => {
      return task.taskHeading.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }




}
