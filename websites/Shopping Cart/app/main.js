"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var home_component_1 = require('./components/home/home.component');
var core_1 = require('@angular/core');
var common_1 = require('@angular/common');
var router_1 = require('@angular/router');
var user_service_1 = require('./services/user.service');
var product_service_1 = require('./services/product.service');
core_1.enableProdMode();
// bootstrap(DashboardComponent);
platform_browser_dynamic_1.bootstrap(home_component_1.HomeComponent, [
    router_1.ROUTER_PROVIDERS, user_service_1.UserService, product_service_1.ProductService,
    core_1.provide(common_1.APP_BASE_HREF, { useValue: '/' }),
    core_1.provide(common_1.LocationStrategy, { useClass: common_1.HashLocationStrategy }) // .../#/crisis-center/
]);
//# sourceMappingURL=main.js.map