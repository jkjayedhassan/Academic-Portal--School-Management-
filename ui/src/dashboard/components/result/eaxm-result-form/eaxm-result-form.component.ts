import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../../dashboard.service';

@Component({
  selector: 'app-eaxm-result-form',
  templateUrl: './eaxm-result-form.component.html',
  styleUrls: ['./eaxm-result-form.component.scss']
})
export class EaxmResultFormComponent {
  examResultForm: FormGroup;

  constructor(private fb: FormBuilder, private dashboardService: DashboardService) {
    this.examResultForm = this.fb.group({
      studentName: ['', Validators.required],
      roll: ['', Validators.required],
      className: [''],
      departmentName: [''],
      subjects: this.fb.array([
        this.createSubjectFormGroup()
      ])
    });
  }

  // Getter for the subjects FormArray
  get subjects(): FormArray {
    return this.examResultForm.get('subjects') as FormArray;
  }

  // Method to create a new FormGroup for a subject
  createSubjectFormGroup(): FormGroup {
    return this.fb.group({
      subjectName: ['', Validators.required],
      marks: ['', [Validators.required, Validators.min(0), Validators.max(100)]],
      grade: ['', Validators.required]
    });
  }

  // Method to add a new subject to the FormArray
  addSubject(): void {
    this.subjects.push(this.createSubjectFormGroup());
  }

  // Method to remove a subject from the FormArray
  removeSubject(index: number): void {
    this.subjects.removeAt(index);
  }

  // Method to handle form submission
  submitForm(): void {
    if (this.examResultForm.valid) {
      const data = this.examResultForm.value;
      this.dashboardService.save(data, 'exam-result').subscribe({
        next: (response) => {
          console.log('Result saved successfully', response);
        },
        error: (error) => {
          console.error('Error saving result', error);
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
