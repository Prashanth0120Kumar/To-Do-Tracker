import { Component } from '@angular/core';
import { TokenService } from '../Services/token.service';
import { Router } from '@angular/router';
import { PRIORITY, TASK } from '../ModelClass/Task';
import { GetTomorrowTasksService } from '../Services/TasksService/get-tomorrow-tasks.service';
import { TomorrowTaskByPriorityService } from '../Services/TasksService/tomorrow-task-by-priority.service';
import { TodayTasksCompletedService } from '../Services/TasksService/completionService.service';
import { TodayHeadEditService } from '../Services/today-head-edit.service';
import { TodayArchiveService } from '../Services/TasksService/ArchiveService';
import { DeleteTaskService } from '../Services/TasksService/delete-task.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent {

  sortAscending = true;
  showTickIcon = false;
  constructor(private service: GetTomorrowTasksService,  
    private router: Router, 
    private priorityService: TomorrowTaskByPriorityService,
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
      , (error) => {
        if (error.error && error.error.message === 'You have no tasks for this day') {
          const tomorrow = new Date();
          tomorrow.setDate(new Date().getDate() + 1);
          this.message = `You have no tasks for ${tomorrow.toLocaleDateString()} (Tomorrow)`;
        } else {
          this.message = 'An error occurred while fetching tasks.';
      }
  });
 
  }



  onCardClick(taskID: number) {
    this.router.navigate(['/task', taskID]);
  }

  fetchTasks() {
    this.priorityService.getTomorrowTasksByPriority().subscribe(
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
          confirm("Task marked as Completed");

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


