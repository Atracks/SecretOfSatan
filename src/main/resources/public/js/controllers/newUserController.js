'use strict';

var newUserController = function($scope, newUserService, loginService) {
    resetError();

    $scope.onCreateUser = function () {
        if(checkLogin()&& checkCasualName() && checkPassword()&& checkPasswordsMatch()) {
            newUserService.createUser($scope.login,$scope.casualName,$scope.password).success(function () {
                resetError();
                replaceToAccount();
            }).error(function () {
                setError("Create user error")
            })
        }
    }
    
    function setError(errorMessage) {
        $scope.error = true;
        $scope.errorMessage = errorMessage;
    }

    function resetError() {
        $scope.error = false;
        $scope.errorMessage = '';
    }

    function checkLogin () {
        var pattern = /^[\w]+$/;
        if(($scope.login === undefined) || ($scope.login.length < 6) || (!pattern.test($scope.login))) {
            var errorMessage = "The login is mandatory must have minimum 6 characters " +
                               "and must contain only letters or numbers";
            setError(errorMessage);
            return false;
        }
        resetError();
        return true;
    }
    
    function checkCasualName() {
        var pattern = /^[\w ]+$/;
        console.info(pattern.test($scope.casualName));
        if(($scope.casualName === undefined)||($scope.casualName.trim() === "")|| (!pattern.test($scope.casualName))) {
            var errorMessage = "Enter your casual name. It must contain only letters, numbers or spaces";
            setError(errorMessage);
            return false;
        } else {
            resetError();
            return true;
        }
    }

    function checkPassword() {
        if(checkPasswordSecurity() && checkPasswordForbiddenSymbols()) {
            return true;
        }
        return false;
    }
    
    function checkPasswordSecurity() {
        var pattern = /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}/;
        if(($scope.password === undefined) || (!pattern.test($scope.password))) {
            var errorMessage = "The password is mandatory must have minimum 6 characters. At least one number and uppercase";
            setError(errorMessage);
            return false;
        } else {
            resetError();
            return true;
        }
    }
    
    function checkPasswordForbiddenSymbols() {
        var forbiddenPattern = /[/"'\\;:]+/;
        if((forbiddenPattern.test($scope.password))) {
            var errorMessage = "The password contains forbidden symbols / \" ' \\ ; :";
            setError(errorMessage);
            return false;
        } else {
            resetError();
            return true;
        }
    }
    
    function checkPasswordsMatch() {
        if(($scope.password === undefined) ||($scope.password != $scope.confirmPassword)) {
            setError("Passwords must match");
            return false;
        }
        resetError();
        return true;
    }

    function replaceToAccount() {
        loginService.login($scope.login,$scope.password).success(function () {
            window.location.replace('#/user-account');
            resetError();
        }).error(function () {
            setError("Login in to account error");
        });
    }
}


