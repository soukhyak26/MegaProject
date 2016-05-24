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
})();
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map