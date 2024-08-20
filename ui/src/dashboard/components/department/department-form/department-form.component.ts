import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['./department-form.component.scss']
})
export class DepartmentFormComponent {
  formGroup: FormGroup;
  departmentList: any[]=[];
  classList: any[] = [];

  constructor(private fb: FormBuilder, private snackbar: MatSnackBar, private dashboardService: DashboardService) {
    this.formGroup = this.fb.group({
      name: ['', [Validators.required]]
    });
    
  }

  ngOnInit(): void {
    this.dashboardService.findAll('studyclass').subscribe(data => {
      this.classList = data.data;
      console.log(data.data);
    });
  }
  isClassSelected(classId: any): boolean {
    return (this.formGroup.get('studyClass')?.value || []).includes(classId);
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

  onSubmit() {
    const formData = this.formGroup.value;
    if (this.formGroup.valid) {
      console.log("Response: ", formData);
      formData.department
      this.dashboardService.save(formData, 'department').subscribe(data => {
        this.snackbar.open(data.message, 'Close', {
          duration: 3000
        });
      });
    }

  }
}