'use strict';

var newUserController = function($scope, newUserService, loginService) {
    resetError();

    $scope.onCreateUser = function () {
        if(checkLogin()&& checkCasualName() && checkPassword()&& checkPasswordsMatch()) {
            newUserService.createUser($scope.login,$scope.casualName,$scope.password).success(function () {
                resetError();
                replaceToAccount();
            }).error(function () {
                setError("Не получилось записать тебя на прием к Санте")
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
            var errorMessage = "Давай выберем подходящий никнейм. Он должен состоять из английских букв и цифр";
            setError(errorMessage);
            return false;
        }
        resetError();
        return true;
    }
    
    function checkCasualName() {
        var pattern = /^([\w ]+|[а-яА-я0-9 ]+)$/;
        if(($scope.casualName === undefined)||($scope.casualName.trim() === "")|| (!pattern.test($scope.casualName))) {
            var errorMessage = "А тебя точно зовут так? Санта признает только имена из букв и пробелов";
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
            var errorMessage = "Что-то с паролем не так. Его длина должна быть больше 6 знаков, иначе гномам он не понравится";
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
            var errorMessage = "Что-то с паролем не так. Похоже, что он содержит запрещенные символы, которые пугают эльфов / \" ' \\ ; :";
            setError(errorMessage);
            return false;
        } else {
            resetError();
            return true;
        }
    }
    
    function checkPasswordsMatch() {
        if(($scope.password === undefined) ||($scope.password != $scope.confirmPassword)) {
            setError("Пароли должны совпадать точь-в-точь");
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
            setError("Кажется, не получилось войти");
        });
    }
}


