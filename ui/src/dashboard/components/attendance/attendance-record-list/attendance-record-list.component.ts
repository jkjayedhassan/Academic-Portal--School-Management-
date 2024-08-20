import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DashboardService } from '../../../dashboard.service';
import { MatTableDataSource } from '@angular/material/table';

// Define an interface for the attendance record

@Component({
  selector: 'app-attendance-record-list',
  templateUrl: './attendance-record-list.component.html',
  styleUrls: ['./attendance-record-list.component.scss']
})
export class AttendanceRecordListComponent {selectedDate: string = '';
  displayedColumns: string[] = ['date','studentName', 'stClass', 'roll','status', 'actions'];
  users= new MatTableDataSource<any>([]);
  constructor(  private dashboardService: DashboardService) {
  
   
  }

 
  ngOnInit(): void {

    this.dashboardService.findAll('attendanceRecord').subscribe({
      next: (data) => {
        this.users = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });


  }

  edit(index: number): void {
    // Implement edit logic here
    console.log('Edit user at index:', index);
  }
}
