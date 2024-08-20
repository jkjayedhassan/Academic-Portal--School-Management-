import { Component } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.scss']
})
export class ClassListComponent {
  classes:any[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe({
      next: (data) => {
        this.classes = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });
  }
  editClass(className: string) {
    console.log('Edit:', className);
    // Implement edit functionality
  }

  deleteClass(className: string) {
    console.log('Delete:', className);
    // Implement delete functionality
  }
}
