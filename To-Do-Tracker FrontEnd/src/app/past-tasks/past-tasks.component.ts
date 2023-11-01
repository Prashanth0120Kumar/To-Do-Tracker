import { Component, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TASK } from '../ModelClass/Task';
import { DeleteTaskService } from '../Services/TasksService/delete-task.service';
import { Router } from '@angular/router';
import { GetPastTasksService } from '../Services/TasksService/get-past-tasks.service';
import { CompletionArchiveService } from '../Services/TasksService/completion-archive.service';
import { TodayTasksCompletedService } from '../Services/TasksService/completionService.service';
import { TodayArchiveService } from '../Services/TasksService/ArchiveService';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-past-tasks',
  templateUrl: './past-tasks.component.html',
  styleUrls: ['./past-tasks.component.css']
})
export class PastTasksComponent {
  message: String = '';
  displayedColumns: string[] = ['taskHeading', 'dueDate', 'dueTime', 'priorityLevel', 'taskContent', 'isComplete', 'deleteTask'];

  dataSource: MatTableDataSource<TASK>;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  constructor(
    private pastService: GetPastTasksService,
    private router: Router,
    private deleteService: DeleteTaskService,
    private completedTasksService: TodayTasksCompletedService,
    private archiveService: TodayArchiveService
  ) {
    this.dataSource = new MatTableDataSource<TASK>([]);
  }

  ngOnInit(): void {
    this.pastService.getTasks().subscribe(
      (data) => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      },
      (error) => {
        if (error.error && error.error.message === 'You have no tasks for this day') {
          this.message = `You have no Archived Task`;
        } else {
          this.message = 'An error occurred while fetching tasks.';
        }
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

  markAsCompleted(taskID: number) {
    const isComplete = true;

    this.completedTasksService.updateTaskCompletion(taskID, isComplete).subscribe(
      (response: any) => {
        console.log('Task marked as completed:', response);
      },
      (error: any) => {
        console.error('Error updating task completion', error);
      }
    );
  }

  markAsArchived(taskID: number) {
    const isArchived = true;

    this.archiveService.updateTaskArchive(taskID, isArchived).subscribe(
      (response: any) => {
        console.log('Task Archived', response);
      },
      (error: any) => {
        console.error('Error', error);
      }
    );
  }
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
}

