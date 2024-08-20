import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required],
      roles: ['', Validators.required]
    });
  }

  ngOnInit(): void {}

  submitForm(): void {
    if (this.formGroup.valid) {
      const formData = this.formGroup.value;
      console.log('Form Data:', formData);
      // Implement further form submission logic here
    } else {
      console.log('Form is invalid');
    }
  }
}
