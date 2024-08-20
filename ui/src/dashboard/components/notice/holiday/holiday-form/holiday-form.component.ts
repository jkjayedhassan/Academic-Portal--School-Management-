import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DashboardService } from '../../../../dashboard.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-holiday-form',
  templateUrl: './holiday-form.component.html',
  styleUrl: './holiday-form.component.scss'
})
export class HolidayFormComponent  {

  holidayForm!: FormGroup;

  constructor(private fb: FormBuilder, 
    private snackBar: MatSnackBar, 
    private dashboardService: DashboardService,
  private router: Router) {}

  ngOnInit(): void {
    this.holidayForm = this.fb.group({
      title: ['', Validators.required],
      date: ['', Validators.required],
      description: ['']
    });
  }

  onSubmit(): void {
    const formData = this.holidayForm.value;
    if (this.holidayForm.valid) {
    this.dashboardService.save(formData, 'holiday').subscribe((response) => {
      
    })

      // Display success message
      this.snackBar.open('Holiday notice submitted successfully!', 'Close', {
        duration: 3000,
      });

      // this.router.navigate(['/dashboard/notice/holiday']);
    }
  }
}