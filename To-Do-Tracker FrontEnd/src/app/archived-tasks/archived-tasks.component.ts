import { MatTableDataSource } from '@angular/material/table';
import { GetArchivedService } from '../Services/TasksService/get-archived.service';
import { TASK } from '../ModelClass/Task';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { DeleteTaskService } from '../Services/TasksService/delete-task.service';
import { CompletionArchiveService } from '../Services/TasksService/completion-archive.service';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-archived-tasks',
  templateUrl: './archived-tasks.component.html',
  styleUrls: ['./archived-tasks.component.css']
})
export class ArchivedTasksComponent implements OnInit {
  message: string = '';
  tasks: TASK[] = [];
  displayedColumns: string[] = ['taskHeading', 'dueDate', 'dueTime', 'priorityLevel', 'taskContent', 'deleteTask'];

  dataSource: MatTableDataSource<TASK>;
  @ViewChild(MatSort)
  sort: MatSort = new MatSort;

  constructor(private archiveService: GetArchivedService, private router: Router, private deleteService: DeleteTaskService, private archiveComplete: CompletionArchiveService) {
    this.dataSource = new MatTableDataSource<TASK>([]);
  }

  isTaskCompleted(task: TASK): boolean {
    return task.isCompleted;
  }

  ngOnInit(): void {
    this.archiveService.getTasks().subscribe(
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




  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
}