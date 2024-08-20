import { Route,  } from "@angular/router";
import { SignupComponent } from "./signup/signup.component";
import { LoginComponent } from "./login/login.component";

export const route: Route[] = [
    {path: 'signup', component: SignupComponent},

    { path: 'login', component: LoginComponent},
    
    { path: '**', redirectTo:'login', pathMatch:"full"}
]