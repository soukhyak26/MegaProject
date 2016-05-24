var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
    switch (arguments.length) {
        case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
        case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
        case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
    }
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
// import {User} from '../models/user.model';
// import { UserService } from '../services/user.service';
// import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS, Router} from 'angular2/router';
var router_deprecated_1 = require('@angular/router-deprecated');
var SignUpComponent = (function () {
    function SignUpComponent() {
    }
    SignUpComponent = __decorate([
        core_1.Component({
            selector: 'sign-up',
            templateUrl: 'app/components/signup/signup.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], SignUpComponent);
    return SignUpComponent;
})();
exports.SignUpComponent = SignUpComponent;
//# sourceMappingURL=signup.component.js.map