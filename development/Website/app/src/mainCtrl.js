'use strict';

mainApp.controller('MainCtrl', function (userService, $mdSidenav, $mdBottomSheet, $log, $q) {

    var self = this;
    self.isLoggedIn = false;
    self.user = { email: "user", password: "user" };

    self.login = login;
    self.toggleList = toggleUsersList;
    function login() {
        self.isLoggedIn = true;
    }

    function toggleUsersList() {
        $mdSidenav('left').toggle();
    }

});
