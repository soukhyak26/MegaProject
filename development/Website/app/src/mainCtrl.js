'use strict';

mainApp.controller('MainCtrl', function (userService, $mdSidenav, $mdBottomSheet, $log, $q) {

    var self = this;
    self.isLoggedIn = false;
    self.user = { email: "Balram", password: "Balram" };
    self.configurations = [{ name: "Application" }, { name: "Product" }, { name: "Subscriber" }, { name: "Benefits" }, { name: "BAM" }];
    self.login = login;
    self.logout = logout;
    self.toggleList = toggleList;

    function logout() {
        self.isLoggedIn = false;
    }

    function login() {
        self.isLoggedIn = true;
    }

    function toggleList() {
        $mdSidenav('left').toggle();
    }

});
