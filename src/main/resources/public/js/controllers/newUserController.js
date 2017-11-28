'use strict';

var newUserController = function($scope, newUserService, loginService) {
    $scope.error = false;
    $scope.errorMessage = '';

    $scope.onCreateUser = function () {
        if(checkUsername()&& checkCasualName() && checkPassword()&& checkPasswordsMatch()) {
            newUserService.createUser($scope.login,$scope.casualName,$scope.password).success(function () {
            replaceToAccount();
            }).error(function () {
                $scope.error = true;
                $scope.errorMessage = "Login already in use";
            })
        }
    }

    function checkUsername () {
        if(($scope.login === undefined) || ($scope.login.length < 6)) {
            $scope.error = true;
            $scope.errorMessage = "The username is mandatory must have minimum 6 characters";
            return false;
        }
        $scope.error = false;
        return true;
    }
    
    function checkCasualName() {
        if(($scope.casualName === undefined)||($scope.casualName.trim() === "")) {
            $scope.error = true;
            $scope.errorMessage = "Enter your casual name";
            return false;
        } else {
            $scope.error = false;
            return true;
        }
    }

    function checkPassword() {
        var pattern = /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}/;
        if(($scope.password === undefined) || (null === $scope.password.match(pattern))) {
            $scope.error = true;
            $scope.errorMessage = "The password is mandatory must have minimum 6 characters. At least one number and uppercase";
            return false;
        }
        $scope.error = false;
        return true;
    }
    
    function checkPasswordsMatch() {
        if(($scope.password === undefined) ||($scope.password === $scope.confirmPassword)) {
            $scope.error = false;
            return true;
        }
        $scope.error = true;
        $scope.errorMessage = "Passwords must match";
        return false;
    }

    function replaceToAccount() {
        loginService.login($scope.login,$scope.password).success(function () {
            window.location.replace('#/user-account');
            $scope.error = false;
        }).error(function () {
            $scope.error = true;
            $scope.errorMessage = "Login in to account error";
        });
    }
}


