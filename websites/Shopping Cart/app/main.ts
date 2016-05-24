import {bootstrap}    from '@angular/platform-browser-dynamic';
import {HomeComponent} from './components/home/home.component';
import {enableProdMode, provide}    from '@angular/core';
import {APP_BASE_HREF, LocationStrategy, HashLocationStrategy} from '@angular/common';
import {ROUTER_PROVIDERS } from '@angular/router';
import { UserService } from './services/user.service';


enableProdMode()

// bootstrap(DashboardComponent);
bootstrap(HomeComponent, [
    ROUTER_PROVIDERS,UserService,
    provide(APP_BASE_HREF, { useValue: '/' }),
    provide(LocationStrategy,
        { useClass: HashLocationStrategy }) // .../#/crisis-center/
]);
