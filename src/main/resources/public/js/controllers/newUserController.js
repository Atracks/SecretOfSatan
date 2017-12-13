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
        if(($scope.login === undefined) || (!pattern.test($scope.login))) {
            var errorMessage = "Login is invalid. Only letters or digits are allowed";
            setError(errorMessage);
            return false;
        }
        resetError();
        return true;
    }
    
    function checkCasualName() {
        var pattern = /^([a-zA-Z ]+|[а-яёА-ЯЁ ]+)$/;
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
        if(($scope.password === undefined) || ($scope.password.length < 6)) {
            var errorMessage = "Password is invalid. Length must be more than 6 characters.";
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
            var errorMessage = "Password is invalid. Contains forbidden symbols / \" ' \\ ; :";
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


