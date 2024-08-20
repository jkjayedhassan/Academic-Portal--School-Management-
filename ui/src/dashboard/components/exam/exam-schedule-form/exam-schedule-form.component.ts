import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-exam-schedule-form',
  templateUrl: './exam-schedule-form.component.html',
  styleUrl: './exam-schedule-form.component.scss'
})
export class ExamScheduleFormComponent {
  dataForm: FormGroup;
  classes:any[] = [];
  subjectList:any[] = [];
 
  constructor( 
    private formBuilder: FormBuilder,
    private dashboardService: DashboardService,
    private snackbar: MatSnackBar )
  {
    this.dataForm = this.formBuilder.group({
      stClass: ['', Validators.required],
      subject: ['', Validators.required],
      examDate: ['', Validators.required],
      examDetails: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe({
      next: (data) => {
        this.classes = data.data;
        console.log(data.data);
      },
   
    });

    this.dashboardService.findAll('subject').subscribe(data => {
      this.subjectList = data.data;
      console.log(data.data);
    });
  }

  onSubmit() {
    const data = this.dataForm.value;
    if (this.dataForm.valid) {
     this.dashboardService.save(data, 'exam').subscribe(response => {
      
     })
     this.snackbar.open(data.message, 'Close', {
      duration: 3000
    });    }
  }
}
