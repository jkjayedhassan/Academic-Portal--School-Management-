import { Component } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.scss']
})
export class DepartmentListComponent {
  departments:any[] = [];

constructor(private dashboardService: DashboardService) {}
  ngOnInit(): void {
    this.dashboardService.findAll('department').subscribe({
      next: (data) => {
        this.departments = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });
  }
  editDepartment(departmentName: string) {
  
  }

  deleteDepartment(departmentName: string) {
    console.log('Delete:', departmentName);
    // Implement delete functionality
  }
}
