import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-class-form',
  templateUrl: './class-form.component.html',
  styleUrls: ['./class-form.component.scss']
})
export class ClassFormComponent implements OnInit {
  formGroup: FormGroup;
  classList: string[] = [];

  constructor(private fb: FormBuilder, private snackbar: MatSnackBar,private dashboardService: DashboardService) {
    this.formGroup = this.fb.group({
      className: ['', Validators.required],
      classTeacher: ['', Validators.required],
      departments: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.generateClassList();
    this.addDepartment();
  }

  get departments(): FormArray {
    return this.formGroup.get('departments') as FormArray;
  }

  addDepartment(): void {
    const departmentGroup = this.fb.group({
      name: [''],
    });
    this.departments.push(departmentGroup);
  }
  removeDepartment(index: number): void {
    this.departments.removeAt(index);
  }


  generateClassList() {
    this.classList = Array.from({ length: 10 }, (_, i) => `Class ${i + 1}`);
  }

  onSubmit() {
    const formData = this.formGroup.value;
    if (this.formGroup.valid) {
    formData.departments = formData.departments.filter((department: any ) => department.name !== '');
      this.dashboardService.save(formData, 'studyclass').subscribe(data => {
        this.snackbar.open(data.message, 'Close', {
          duration: 3000
        });
      });
    }
    
}

}
