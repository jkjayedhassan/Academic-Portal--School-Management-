import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Response, ResponseStatus } from '../../model/Response.model';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {


  loginForm: FormGroup;
  constructor(private fb: FormBuilder,
    private homeService: HomeService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }
  onSubmit() {
    if (this.loginForm.valid) {
      this.homeService.login(this.loginForm.value).subscribe((response: Response<any>) => {
        // console.log(response);
        // console.log(response.status == ResponseStatus.SUCCESS);
        if (response.status == ResponseStatus.SUCCESS) {
          localStorage.setItem('token', response.data)
          this.router.navigate(['/dashboard'])
        }

      })
    }
  }
}