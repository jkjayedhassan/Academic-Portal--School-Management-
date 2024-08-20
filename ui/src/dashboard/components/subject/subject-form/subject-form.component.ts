import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.scss']
})
export class SubjectFormComponent {
  formGroup: FormGroup;
  classList: any[] = [];
  constructor(private fb: FormBuilder, private snackbar: MatSnackBar, private dashboardService: DashboardService) {
    this.formGroup = this.fb.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      studyClass: [[]]
    });
  }

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe(data => {
      this.classList = data.data;
      console.log(data.data);
    });
  }

  onClassChange(event: any) {
    const classId = event.target.value;
    const currentClasses = this.formGroup.get('studyClass')?.value || [];
    if (event.target.checked) {
      this.formGroup.get('studyClass')?.setValue([...currentClasses, classId]);
    } else {
      this.formGroup.get('studyClass')?.setValue(currentClasses.filter((id: any) => id !== classId));
    }
  }
  isClassSelected(classId: any): boolean {
    return (this.formGroup.get('studyClass')?.value || []).includes(classId);
  }
  onSubmit(): void {
    const subject = this.formGroup.value;
    if (this.formGroup.valid) {
      subject.studyClass = subject.studyClass.map((classId: any) => ({ id: Number(classId) }));
      console.log("Response: ", subject);
      this.dashboardService.save(subject, 'subject').subscribe(data => {
        this.snackbar.open(data.message, 'Close', {
          duration: 3000
        });
      })
    }
  }
}
