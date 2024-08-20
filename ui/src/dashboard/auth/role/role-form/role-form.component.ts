import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RoleService } from '../role.service';

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrl: './role-form.component.scss'
})
export class RoleFormComponent implements OnInit {
  formGroup!: FormGroup;
    submitted = false;
    endPoint = "role";
  
    constructor(private formBuilder: FormBuilder, private service: RoleService) { }
  
    ngOnInit() {
      this.createForm();
    }
  
    createForm() {
      this.formGroup= this.formBuilder.group({
        name: ['', Validators.required],
        permissions: ['', Validators.required]
      });
    }
  
    submitForm() {
      this.submitted = true;
  
      if (this.formGroup.invalid) {
        // Handle validation errors, e.g., mark fields as touched and display error messages
        return;
      }
  
      //const roleData: Role = this.formGroup.value;
      //this.service.save(roleData,this.endPoint ).subscribe(response => {
        // Handle the response from the service (e.g., show a success message)
        // Optionally, you can reset the form after successful submission
        this.formGroup.reset();
        this.submitted = false;
      //});
    }
  }
  