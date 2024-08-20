import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DashboardService } from '../../../dashboard.service';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.scss']
})
export class StudentFormComponent implements OnInit {
  dataForm: FormGroup;
  photoError: boolean = false;
  photoPreview: string | ArrayBuffer | null = null;
  subjectList: any[] = [];
  deptList: any[] = [];
  classes: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private studentService: StudentService,
    private dashboardService: DashboardService,
    private snackbar: MatSnackBar,
    private router: Router
  ) {
    this.dataForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      roll: ['', Validators.required],
      address: [''],
      stClass: ['', Validators.required],
      session: [''],
      subjects: [[]],
      dept: this.formBuilder.group({
        id: [0, Validators.required]
      }),
      user: this.formBuilder.group({
        username: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(2)]]
      })
    });
  }

  ngOnInit(): void {
    this.dashboardService.findAll('department').subscribe({
      next: (data) => {
        this.deptList = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });
    this.dashboardService.findAll('subject').subscribe(data => {
      this.subjectList = data.data;
      console.log(data.data);
    });

    this.dashboardService.findAll('studyclass').subscribe({
      next: (data) => {
        this.classes = data.data;
        console.log(data.data);
      },
      error(err) {
        console.log(err);
      },
    });
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
      this.dataForm.patchValue({
        photo: file
      });
      this.photoError = false;
    }
  }
  onClassChange(event: any) {
    const classId = event.target.value;
    if (classId) {
      const currentClass = this.classes.find((c: any) => c.id == classId);
      this.deptList = currentClass.departments;
    }
  }
  onSubjectChange(event: any) {
    const subjectId = event.target.value;
    const currentSubjects = this.dataForm.get('subjects')?.value || [];
    if (event.target.checked) {
      this.dataForm.get('subjects')?.setValue([...currentSubjects, subjectId]);
    } else {
      this.dataForm.get('subjects')?.setValue(currentSubjects.filter((id: any) => id !== subjectId));
    }
  }

  isSubjectSelected(subjectId: any): boolean {
    return (this.dataForm.get('subjects')?.value || []).includes(subjectId);
  }


  onSubmit() {
    if (this.dataForm.invalid) {
      if (!this.dataForm.get('photo')?.value) {
        this.photoError = true;
      }
      return;
    }

    const student = { ...this.dataForm.value };


    if (!student.dept.id || student.dept.id === 0) {
      student.dept = null;
    } else {
      student.dept.id = Number(student.dept.id);
    }

    if (!student.stClass || student.stClass === 0) {
      student.stClass = null;
    } else {
      student.stClass = { id: Number(student.stClass) };
    }

    student.user.name = `${student.firstName} ${student.lastName}`;
    student.photo = this.photoPreview;
    student.subjects = student.subjects.map((subject: any) => ({ id: Number(subject) }));
    console.log(student);
    this.dashboardService.save(student, 'student').subscribe(data => {
      this.snackbar.open(data.message, 'Close', {
        duration: 3000
      });
    });
  }
}
