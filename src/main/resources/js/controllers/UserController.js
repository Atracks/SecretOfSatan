'use strict';

var AdminAccountController = function($scope, $http, adminAccountService) {
    $scope.user = {};
    getUsers();

    $scope.deleteUser() = function(login) {
        userService.deleteUser(login).success(function() {
        getUsers();
        }).error(function setError("delete user error"));
    };

    $scope.letsRock() = function() {
        userService.letsRock().success(function() {
        getUsers();
        }).error(function setError("michael krug"));
    }

    function getUsers() {
        userService.getUsers().success(function (users) {
        $scope.users = users;
        }).error(function () {setError("get users error");});
    };

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
