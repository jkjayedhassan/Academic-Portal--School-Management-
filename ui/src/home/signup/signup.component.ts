import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HomeService } from '../home.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signupForm: FormGroup;

  constructor(private fb: FormBuilder,
    private homeService: HomeService,
    private snackbar: MatSnackBar,
    private router: Router,

  ) {
    this.signupForm = this.fb.group({
      name: [''],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(2)]]
    });
  }
  onSubmit()  {
    if (this.signupForm.valid) {
      this.homeService.signup(this.signupForm.value).subscribe(() => {
        this.snackbar.open('Signup successful! Please login.', 'Close', {
          duration: 3000
        });
        this.router.navigate(['/home/login']);
      });
    }
  }
}
