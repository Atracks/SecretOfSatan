'use strict';

var loginController = function($scope, $http, loginService) {
    $scope.error = false;
    $scope.errorMessage = '';

    $scope.onLogin = function () {
        if((checkUsername($scope.username)) && (checkPassword($scope.password))) {
            loginService.login($scope.username, $scope.password).then(function (response) {
                if (response.data === 'ok') {
                    $scope.error = false;
                    replaceToAccount();
                } else {
                    $scope.error = true;
                    $scope.errorMessage = "Wrong username or password"
                }
            })
        }
    };

    function checkUsername () {
        if(($scope.username === undefined) || ($scope.username.length < 6)) {
            $scope.error = true;
            $scope.errorMessage = "The username is mandatory must have minimum 6 characters";
            return false;
        }
        $scope.error = false;
        return true;
    }
    
    function checkPassword() {
        var pattern = /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}/;
        if(null === $scope.password.match(pattern)) {
            $scope.error = true;
            $scope.errorMessage = "The password is mandatory must have minimum 6 characters. At least one number and uppercase";
            return false;
        }
        $scope.error = false;
        return true;
    }
    
    function replaceToAccount() {
        loginService.getCurrentUserRole().success(function (currentUserRole) {
            if(currentUserRole[0].authority === "ROLE_ADMIN") {
                window.location.replace('#/admin-account');
            }
            if(currentUserRole[0].authority === "ROLE_USER") {
                window.location.replace('#/user-account');
            }
            $scope.error = false;
            window.location.reload();
        }).error(function () {
            $scope.error = true;
            $scope.errorMessage = 'Get user role error';
        })
    }
}

