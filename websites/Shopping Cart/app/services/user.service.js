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
var user_model_1 = require('../models/user.model');
var UserService = (function () {
    function UserService() {
        this.count = 0;
        this.loggedInUser = new user_model_1.User();
        console.log('service created...');
    }
    UserService.prototype.setCount = function (_count) {
        console.log('setting count: ' + _count);
        this.count = _count;
    };
    UserService.prototype.getCount = function () {
        return this.count;
    };
    UserService.prototype.setUserLoggedInStatus = function (status) {
        this.isUserLoggedIn = status;
    };
    UserService.prototype.setLoggedInUser = function (user) {
        if (!user) {
            this.loggedInUser = null;
        }
        else {
            this.loggedInUser.id = user.id;
            this.loggedInUser.password = user.password;
        }
    };
    UserService.prototype.getLoggedInUser = function () {
        return this.loggedInUser;
    };
    UserService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], UserService);
    return UserService;
}());
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map