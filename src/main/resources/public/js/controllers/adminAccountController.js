'use strict';

var adminAccountController = function($scope, $http, adminAccountService, loginService) {
    $scope.isLetsRockAllowed = false;
    $scope.user = {};
    $scope.errorMessage = '';
    $scope.error = false;
    $scope.successMessage = '';
    $scope.isFullTable = false;
    checkLetsRockAllowed();
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
            checkLetsRockAllowed();
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

    function checkLetsRockAllowed() {
        loginService.checkIsRegistrationAllowed().success(function (response) {
            if("true" === response) {
                $scope.isLetsRockAllowed = true;
            } else {$scope.isLetsRockAllowed = false;}
        }).error(function () {
            setError("Internal service error. Please contact with support team");
        })
    }
}
