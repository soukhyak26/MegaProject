import {Component} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';

import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
    selector: 'home',
    templateUrl: 'app/components/home/home.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [ROUTER_PROVIDERS]
})
@RouteConfig([
    {
        path: '/',
        name: 'Login',
        component: LoginComponent,
        useAsDefault: true
    },
    {
        path: '/signup',
        name: 'SignUp',
        component: SignUpComponent
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardComponent
    }
])
export class HomeComponent {
}