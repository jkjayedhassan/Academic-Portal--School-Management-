import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-exam-schedule-list',
  templateUrl: './exam-schedule-list.component.html',
  styleUrls: ['./exam-schedule-list.component.scss']
})
export class ExamScheduleListComponent implements OnInit {

  exams: any[] = [];
  filteredExams: any[] = [];
  currentSortColumn: string = '';
  isAscending: boolean = true;

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.dashboardService.findAll('exam').subscribe({
      next: (data) => {
        this.exams = data.data;
        this.filteredExams = this.exams; // Initialize the filtered list
      },
      error(err) {
        console.log(err);
      }
    });
  }

  onSearch(event: Event) {
    const target = event.target as HTMLInputElement;
    const searchTerm = target.value.toLowerCase();
  
    this.filteredExams = this.exams.filter(exam =>
      exam.stClass.toLowerCase().includes(searchTerm) ||
      exam.subject.toLowerCase().includes(searchTerm) ||
      (exam.examDate && exam.examDate.toString().includes(searchTerm)) ||
      exam.examDetails.toLowerCase().includes(searchTerm)
    );
  }
  
  delete(exam: any) {
  
      this.dashboardService.delete(exam.id, 'exam').subscribe({
        next: () => {
          alert('Exam deleted successfully!');
          this.ngOnInit();
        },
        error: (err) => {
          console.log(err);
        }
      });
    
    }
  onSort(column: string) {
    if (this.currentSortColumn === column) {
      this.isAscending = !this.isAscending;
    } else {
      this.currentSortColumn = column;
      this.isAscending = true;
    }

    this.filteredExams = this.filteredExams.sort((a, b) => {
      const valA = a[column];
      const valB = b[column];
      if (valA < valB) return this.isAscending ? -1 : 1;
      if (valA > valB) return this.isAscending ? 1 : -1;
      return 0;
    });
  }
}
