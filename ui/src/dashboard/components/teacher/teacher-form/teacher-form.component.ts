import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DashboardService } from '../../../dashboard.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-teacher-form',
  templateUrl: './teacher-form.component.html',
  styleUrls: ['./teacher-form.component.scss']
})
export class TeacherFormComponent implements OnInit {

  formGroup: FormGroup;
  photoError: boolean = false;
  photoPreview: string | ArrayBuffer | null = null;

  constructor(
    private fb: FormBuilder,
    private teacherService: TeacherService,
    private dashboardService: DashboardService,
    private snackbar: MatSnackBar,
    private router: Router
  ) { 
    this.formGroup = this.fb.group({
      photo: [null, Validators.required],
      fullName: ['', Validators.required],
      contactNumber: ['', Validators.required],
      department: ['', Validators.required],
      employeeId: ['', Validators.required],
      academicSession: ['', Validators.required],
      address: ['', Validators.required],
      
      user: this.fb.group({
        username: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(2)]]
      })
      
    });
  }

  ngOnInit(): void {
  }

 onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.photoPreview = reader.result;
        console.log(this.photoPreview);
      };
      reader.readAsDataURL(file);
      this.formGroup.patchValue({
        photo: file
      });
      this.photoError = false;
    }
  }

  submitForm() {
    if (this.formGroup.invalid) {
      if (!this.formGroup.get('photo')?.value) {
        this.photoError = true;
      }
      return;
    }

    const teacher = this.formGroup.value;
    teacher.user.name = `${teacher.fullName} `;
    teacher.photo = this.photoPreview;

    this.dashboardService.save(teacher, 'teacher').subscribe(data => {
      this.snackbar.open(data.message, 'Close', {
        duration: 3000
      });
    });
  }
}
