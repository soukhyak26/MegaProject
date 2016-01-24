'use strict';
var mainApp = angular.module('mainApp',
    [
        'ngMaterial',
    ]);

mainApp.directive('login', function () {
    return {
        restrict: 'E',
        templateUrl: 'src/directives/login/login.html',
        controller: 'LoginCtrl'
    };
});
