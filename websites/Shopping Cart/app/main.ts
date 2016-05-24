import {bootstrap}    from '@angular/platform-browser-dynamic';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {enableProdMode, provide}    from '@angular/core';
import {APP_BASE_HREF, LocationStrategy, HashLocationStrategy} from '@angular/common';
import {ROUTER_PROVIDERS } from '@angular/router';


enableProdMode()

// bootstrap(DashboardComponent);
bootstrap(DashboardComponent, [
    ROUTER_PROVIDERS,
    provide(APP_BASE_HREF, { useValue: '/' }),
    provide(LocationStrategy,
        { useClass: HashLocationStrategy }) // .../#/crisis-center/
]);
