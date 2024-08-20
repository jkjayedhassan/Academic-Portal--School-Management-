import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-permission-form',
  templateUrl: './permission-form.component.html',
  styleUrls: ['./permission-form.component.scss']
})
export class PermissionFormComponent implements OnInit {
  formGroup: FormGroup = this.fb.group({
    name: ['', Validators.required],
    componentKey: ['', Validators.required],
    description: [''],
    category: ['']
  });

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.formGroup = this.fb.group({
      name: ['', Validators.required],
      componentKey: ['', Validators.required],
      description: [''],
      category: ['']
    });
  }

  submitForm() {
    if (this.formGroup.valid) {
      // Handle form submission
      console.log(this.formGroup.value);
    }
  }
}
