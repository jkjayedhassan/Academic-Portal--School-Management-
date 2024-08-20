import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../../dashboard.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-holiday-list',
  templateUrl: './holiday-list.component.html',
  styleUrls: ['./holiday-list.component.scss']
})
export class HolidayListComponent implements OnInit {

  holidayNotices: any[] = [];
  filteredHolidayNotices: any[] = [];
  totalRecords: number = 0;
  pageSize: number = 10;
  page: number = 1;
  currentSortColumn: string = 'date';
  isAscending: boolean = true;
  search: string = '';

  constructor(private dashboardService: DashboardService, private snackbar: MatSnackBar) {}

  ngOnInit(): void {
    this.loadHolidayNotices();
  }

  loadHolidayNotices(): void {
    const params = {
      page: this.page - 1, // Backend usually expects 0-based page index
      size: this.pageSize,
      sort: `${this.currentSortColumn},${this.isAscending ? 'asc' : 'desc'}`,
      search: this.search
    };

    this.dashboardService.findAll('holiday').subscribe({
      next: (response) => {
        this.holidayNotices = response.data;
        this.filteredHolidayNotices = this.holidayNotices;
        this.totalRecords = response.data;
      },
      error: (err) => {
        console.error('Error loading holiday notices', err);
        this.snackbar.open('Failed to load holiday notices', 'Close', {
          duration: 3000,
        });
      }
    });
  }

  onPageChange(event: PageEvent): void {
    this.page = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.loadHolidayNotices();
  }

  onSortChange(column: string): void {
    if (this.currentSortColumn === column) {
      this.isAscending = !this.isAscending;
    } else {
      this.currentSortColumn = column;
      this.isAscending = true;
    }
    this.loadHolidayNotices();
  }

  onSearchChange(search: string): void {
    this.search = search;
    this.loadHolidayNotices();
  }


  onDelete(id: number): void {
    if (confirm('Are you sure you want to delete this holiday notice?')) {
      this.dashboardService.delete(id, 'holiday').subscribe({
        next: () => {
          this.loadHolidayNotices();
          this.snackbar.open('Holiday notice deleted successfully!', 'Close', {
            duration: 3000,
          });
        },
        error: (err) => {
          console.error('Error deleting holiday notice', err);
          this.snackbar.open('Failed to delete holiday notice', 'Close', {
            duration: 3000,
          });
        }
      });
    }
  }
}
