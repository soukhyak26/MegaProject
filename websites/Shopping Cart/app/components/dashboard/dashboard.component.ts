import {Component, ViewChild} from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';
import { LoginComponent } from '../login/login.component';
import { SignUpComponent } from '../signup/signup.component';

declare var jQuery: any;

@Component({
    selector: 'dashboard',
    templateUrl: 'app/components/dashboard/dashboard.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [    
        ROUTER_PROVIDERS
    ]
})

export class DashboardComponent {

}
