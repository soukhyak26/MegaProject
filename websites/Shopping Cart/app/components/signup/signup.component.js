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
var user_model_1 = require('../../models/user.model');
var user_service_1 = require('../../services/user.service');
var router_deprecated_1 = require('@angular/router-deprecated');
var SignUpComponent = (function () {
    function SignUpComponent(userService, _router) {
        this.userService = userService;
        this._router = _router;
        this.allUsersKey = "users";
        this.allUsers = JSON.parse(localStorage.getItem(this.allUsersKey));
        if (!this.allUsers) {
            this.allUsers = new Array();
        }
    }
    SignUpComponent.prototype.signup = function () {
        if (this.isUserNameTaken()) {
            Materialize.toast(this.username + ' user name already taken. Please try different one.!', 3000);
            return;
        }
        console.log('UserName: ' + this.username);
        console.log('password: ' + this.password);
        console.log('confirmPassword: ' + this.confirmPassword);
        if (this.password !== this.confirmPassword) {
            Materialize.toast('Password didn\'t match!', 3000);
            return;
        }
        var newUser = new user_model_1.User();
        newUser.id = this.username;
        newUser.password = this.password;
        this.allUsers.push(newUser);
        localStorage.setItem(this.allUsersKey, JSON.stringify(this.allUsers));
        Materialize.toast('Welcome ' + this.username, 3000);
        this.userService.setUserLoggedInStatus(true);
        this.userService.setLoggedInUser(newUser);
        this._router.navigate(['Dashboard']);
    };
    SignUpComponent.prototype.isUserNameTaken = function () {
        var _this = this;
        var user = this.allUsers.find(function (x) { return x.id === _this.username; });
        if (user)
            return true;
        else
            return false;
    };
    SignUpComponent = __decorate([
        core_1.Component({
            selector: 'sign-up',
            templateUrl: 'app/components/signup/signup.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [user_service_1.UserService, router_deprecated_1.Router])
    ], SignUpComponent);
    return SignUpComponent;
})();
exports.SignUpComponent = SignUpComponent;
//# sourceMappingURL=signup.component.js.map