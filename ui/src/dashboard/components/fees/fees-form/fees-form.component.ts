import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-fees-form',
  templateUrl: './fees-form.component.html',
  styleUrls: ['./fees-form.component.scss']
})
export class FeesFormComponent implements OnInit {
  feesForm: FormGroup;
  receipt: any = null;
  paymentHistory: any[] = [];
  classes: any[] = [];
  // studentList: any[] = [];
  // isCashSelected: boolean = false; // Added property to track if "Cash" is selected

  constructor(private fb: FormBuilder, private dashboardService: DashboardService, private snackbar: MatSnackBar) {
    this.feesForm = this.fb.group({
      stClassName: ['', Validators.required],
      studentName: ['', Validators.required],
      roll: ['', Validators.required],
      amount: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      paymentReason: ['', Validators.required],
      transactionId: ['', Validators.required],
      paymentDate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe({
      next: (data) => {
        this.classes = data.data;
      }
    });

    // this.dashboardService.findAll('student').subscribe(data => {
    //   this.studentList = data.data;
    // });

  
  }

  // onPaymentMethodChange(paymentMethod: string): void {
  //   this.isCashSelected = paymentMethod === 'cash';
    
  
  //   if (this.isCashSelected) {
  //     this.feesForm.get('transactionId')?.setValue('');
  //   }
  // }

  onSubmit(): void {
    if (this.feesForm.valid) {
      const formData = this.feesForm.value;
      this.dashboardService.save(formData, 'payment').subscribe({
        next: (response) => {
          this.receipt = response;
          this.snackbar.open('Payment successful!', 'Close', { duration: 3000 });
          // this.loadPaymentHistory();
        },
        error: (err) => {
          this.snackbar.open('Payment failed. Please try again.', 'Close', { duration: 3000 });
        }
      });
    }
  }

  // loadPaymentHistory(): void {
  //   const studentId = this.feesForm.get('studentId')?.value;
  //   if (studentId) {
  //     this.dashboardService.getPaymentHistory(studentId).subscribe({
  //       next: (data) => {
  //         this.paymentHistory = data;
  //       },
  //       error: (err) => {
  //         console.log(err);
  //       }
  //     });
  //   }
  // }

  // downloadReceipt(): void {
  //   const receiptData = `
  //     Transaction ID: ${this.receipt.transactionId}
  //     Amount: ${this.receipt.amount} BDT
  //     Payment Method: ${this.receipt.paymentMethod}
  //   `;
  //   const blob = new Blob([receiptData], { type: 'text/plain' });
  //   const url = window.URL.createObjectURL(blob);
  //   const a = document.createElement('a');
  //   a.href = url;
  //   a.download = `receipt_${this.receipt.transactionId}.txt`;
  //   a.click();
  //   window.URL.revokeObjectURL(url);
  // }
}
