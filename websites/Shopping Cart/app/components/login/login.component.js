"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_deprecated_1 = require('@angular/router-deprecated');
var user_service_1 = require('../../services/user.service');
var LoginComponent = (function () {
    function LoginComponent(userService, _router) {
        this.userService = userService;
        this._router = _router;
        this.allUsersKey = "users";
        this.userService = userService;
        this.allUsers = JSON.parse(localStorage.getItem(this.allUsersKey));
        if (!this.allUsers) {
            this.allUsers = new Array();
        }
    }
    LoginComponent.prototype.login = function () {
        var _this = this;
        console.log('setting count from login...');
        this.userService.setCount(10);
        var user = this.allUsers.find(function (x) { return x.id === _this.username && x.password === _this.password; });
        if (user) {
            Materialize.toast('Welcome ' + this.username, 3000);
            this.userService.setUserLoggedInStatus(true);
            this.userService.setLoggedInUser(user);
            this._router.navigate(['Dashboard']);
        }
        else {
            this.userService.setUserLoggedInStatus(false);
            this.userService.setLoggedInUser(null);
            Materialize.toast('Incorrect username or password', 3000);
        }
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'login',
            templateUrl: 'app/components/login/login.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [user_service_1.UserService, router_deprecated_1.Router])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map