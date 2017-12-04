'use strict';

var adminAccountController = function($scope, $http, adminAccountService) {
    $scope.user = {};
    $scope.errorMessage = '';
    $scope.error = false;
    $scope.successMessage = '';
    $scope.isFullTable = false;
    getUsers();

    $scope.deleteUser = function(login) {
        adminAccountService.deleteUser(login).success(function() {
            resetError();
            getUsers();
        }).error(function() {setError("Delete user error");});
    };

    $scope.letsRock = function() {
        adminAccountService.letsRock().success(function() {
            resetError();
            getUsers();
            $scope.successMessage = "Rock will never die!!";
        }).error(function() {setError("Michael Circle");});
    }

    function getUsers() {
        adminAccountService.getUsers().success(function (users) {
            resetError();
            $scope.users = users;
        }).error(function () {setError("Get users error");});
    };

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
        $scope.successMessage = '';
    }
    
    function resetError() {
        $scope.error = false;
    }

    $scope.showFullTable = function () {
        $scope.isFullTable = true;
    }

    $scope.hideFullTable = function () {
        $scope.isFullTable = false;
    }
}
