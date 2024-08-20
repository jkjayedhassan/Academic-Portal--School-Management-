import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-eaxm-result-list',
  templateUrl: './eaxm-result-list.component.html',
  styleUrls: ['./eaxm-result-list.component.scss']
})
export class EaxmResultListComponent implements OnInit {
  examResults: any[] = []; // Replace 'any' with the actual type for exam results if available
  filterForm: FormGroup;

  constructor(private fb: FormBuilder, private dashboardService: DashboardService) {
    this.filterForm = this.fb.group({
      className: [''],
      departmentName: ['']
    });
  }

  ngOnInit(): void {
    this.fetchExamResults(); // Load results initially
  }

  fetchExamResults(className?: string, departmentName?: string): void {
    this.dashboardService.getAll('exam-result', { className, departmentName }).subscribe({
      next: (response) => {
        this.examResults = response.data; // Adjust based on the actual response structure
      },
      error: (error) => {
        console.error('Error fetching exam results', error);
      }
    });
  }

  applyFilter(): void {
    const { className, departmentName } = this.filterForm.value;
    this.fetchExamResults(className, departmentName);
  }
}
