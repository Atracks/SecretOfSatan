'use strict';

var adminAccountController = function($scope, $http, adminAccountService) {
    $scope.user = {};
    $scope.errorMessage = '';
    $scope.error = false;
    getUsers();

    $scope.deleteUser = function(login) {
        adminAccountService.deleteUser(login).success(function() {
        getUsers();
        }).error(function() {setError("delete user error");});
    };

    $scope.letsRock = function() {
        adminAccountService.letsRock().success(function() {
        getUsers();
        }).error(function() {setError("michael krug");});
    }

    function getUsers() {
        adminAccountService.getUsers().success(function (users) {
        $scope.users = users;
        }).error(function () {setError("get users error");});
    };

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
