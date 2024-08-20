import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.scss']
})
export class SubjectListComponent implements OnInit {

  classes: any[] = [];
  subjectList: any[] = [];
  selectedClassId: number | null = null;  // Property to store the selected class ID

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe(data => {
      this.classes = data.data;
      console.log(data.data);
    });
  }

  onClassChange(event: any): void {
    this.selectedClassId = event.target.value;
    if (this.selectedClassId) {
      // Fetch subjects based on the selected class ID
      this.dashboardService.findAll(`subject?classId=${this.selectedClassId}`).subscribe(data => {
        this.subjectList = data.data;
        console.log(data.data);
      });
    } else {
      // If no class is selected, clear the subject list
      this.subjectList = [];
    }
  }
}
