import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../dashboard.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-fees-list',
  templateUrl: './fees-list.component.html',
  styleUrls: ['./fees-list.component.scss']
})
export class FeesListComponent implements OnInit {

  fees: any[] = [];
  filteredPayments: any[] = [];
  currentSortColumn: string = '';
  isAscending: boolean = true;

  constructor(private dashboardService: DashboardService, private snackbar: MatSnackBar) {}

  ngOnInit(): void {
    this.dashboardService.findAll('payment').subscribe({
      next: (data) => {
        this.fees = data.data;
        this.filteredPayments = this.fees; // Initialize the filtered list
      },
      error: (err) => {
        console.log(err);
        this.snackbar.open('Failed to load fees list', 'Close', {
          duration: 3000,
        });
      }
    });
  }

  onSearch(event: Event) {
    const target = event.target as HTMLInputElement;
    const searchTerm = target.value.toLowerCase();

    this.filteredPayments = this.fees.filter(fee =>
      fee.studentName.toLowerCase().includes(searchTerm) ||
      fee.roll.toLowerCase().includes(searchTerm) ||
      fee.stClassName.toLowerCase().includes(searchTerm) ||
      fee.amount.toString().includes(searchTerm) ||
      fee.paymentMethod.toLowerCase().includes(searchTerm) ||
      fee.transactionId.toLowerCase().includes(searchTerm) ||
      fee.paymentReason.toLowerCase().includes(searchTerm) ||
      (fee.paymentDate && fee.paymentDate.toString().includes(searchTerm))
    );
  }

  delete(fee: any) {
    if (confirm('Are you sure you want to delete this payment?')) {
      this.dashboardService.delete(fee.id, 'payment').subscribe({
        next: () => {
          alert('Payment deleted successfully!');
          this.ngOnInit(); // Reload the list after deletion
        },
        error: (err) => {
          console.log(err);
          this.snackbar.open('Failed to delete payment', 'Close', {
            duration: 3000,
          });
        }
      });
    }
  }

  onSort(column: string) {
    if (this.currentSortColumn === column) {
      this.isAscending = !this.isAscending;
    } else {
      this.currentSortColumn = column;
      this.isAscending = true;
    }

    this.filteredPayments = this.filteredPayments.sort((a, b) => {
      const valA = a[column];
      const valB = b[column];
      if (valA < valB) return this.isAscending ? -1 : 1;
      if (valA > valB) return this.isAscending ? 1 : -1;
      return 0;
    });
  }
}
