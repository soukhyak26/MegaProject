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
var SearchUserPipe = (function () {
    function SearchUserPipe() {
    }
    SearchUserPipe.prototype.transform = function (users, args) {
        if (args) {
            var searchUserName = args[0];
            return users.filter(function (item) { return item.name.indexOf(searchUserName) !== -1; });
        }
        else {
            return users;
        }
    };
    SearchUserPipe = __decorate([
        core_1.Pipe({
            name: 'searchUserFilter',
            pure: false
        }), 
        __metadata('design:paramtypes', [])
    ], SearchUserPipe);
    return SearchUserPipe;
})();
exports.SearchUserPipe = SearchUserPipe;
//# sourceMappingURL=searchUser.pipe.js.map