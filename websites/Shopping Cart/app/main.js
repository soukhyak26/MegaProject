var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var dashboard_component_1 = require('./components/dashboard/dashboard.component');
var core_1 = require('@angular/core');
var common_1 = require('@angular/common');
var router_1 = require('@angular/router');
core_1.enableProdMode();
// bootstrap(DashboardComponent);
platform_browser_dynamic_1.bootstrap(dashboard_component_1.DashboardComponent, [
    router_1.ROUTER_PROVIDERS,
    core_1.provide(common_1.APP_BASE_HREF, { useValue: '/' }),
    core_1.provide(common_1.LocationStrategy, { useClass: common_1.HashLocationStrategy }) // .../#/crisis-center/
]);
//# sourceMappingURL=main.js.map