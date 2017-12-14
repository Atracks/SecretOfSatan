'use strict';

var loginController = function($scope, $http, loginService) {
    $scope.isRegistrationAllowed = false;
    checkIsRegistrationAllowed();
    resetError();
    replaceToAccount();


    $scope.onLogin = function () {
        loginService.login($scope.login, $scope.password).then(function (response) {
            if (response.data === 'ok') {
                resetError();
                replaceToAccount();
            } else {
                setError("Похоже, что Санта не помнит тебя. Проверь свой никнейм и секретный пароль");
            }
        })
    };

    function setError(errorMessage) {
        $scope.error = true;
        $scope.errorMessage = errorMessage;
    }

    function resetError() {
        $scope.error = false;
        $scope.errorMessage = '';
    }

    function replaceToAccount() {
        loginService.getCurrentUserRole().success(function (currentUserRole) {
            var currentLocation = window.location;
            if(currentUserRole[0].authority === "ROLE_ADMIN") {
                window.location.replace('#/admin-account');
            }
            if(currentUserRole[0].authority === "ROLE_USER") {
                window.location.replace('#/user-account');
            }
            if((currentUserRole[0].authority != undefined) && (currentLocation === window.location)) {
                window.location.reload(true);
            }
            resetError();
        }).error(function () {
            setError("Не получилось определить твою роль на празднике");
        })
    }
    
    function checkIsRegistrationAllowed() {
        loginService.checkIsRegistrationAllowed().success(function (response) {
            if("true" === response) {
                $scope.isRegistrationAllowed = true;
            } else {$scope.isRegistrationAllowed = false;}
        }).error(function () {
            setError("Упс. Кажется, что-то пошло не так. Попробуйте связаться с новогодней службой поддержки: atracks@bk.ru, athest@mail.ru");
        })
    }
}



