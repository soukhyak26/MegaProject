import {Component, ViewChild} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';

declare var jQuery: any;

@Component({
    selector: 'my-app',
    templateUrl: 'app/components/dashboard/dashboard.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [    
        ROUTER_PROVIDERS
    ]
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
    }
])
export class DashboardComponent {

}
