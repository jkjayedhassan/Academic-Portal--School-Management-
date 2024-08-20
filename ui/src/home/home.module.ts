import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { RouterModule } from '@angular/router';
import { route as homeRoutes } from './home.routes';
import { HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { HomeService } from './home.service';


@NgModule({
  declarations: [
    SignupComponent,
    LoginComponent,
    
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(homeRoutes),
    ReactiveFormsModule,
    MatSnackBarModule,
  ],
  exports :[RouterModule],
  providers: [provideHttpClient(withInterceptorsFromDi()),
    HomeService
  ]
  
})
export class HomeModule { }
