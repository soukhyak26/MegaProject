'use strict';

mainApp.controller('MainCtrl', function (userService, $mdSidenav, $mdBottomSheet, $log, $q) {

    var self = this;
    self.isLoggedIn = false;
    self.user = { email: "user", password: "user" };

    self.login = login;

    function login() {
        self.isLoggedIn = true;

    }
});
