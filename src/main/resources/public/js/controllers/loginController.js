'use strict';

var loginController = function($scope, $http, loginService) {
    resetError();
    replaceToAccount();

    $scope.onLogin = function () {
        loginService.login($scope.login, $scope.password).then(function (response) {
            if (response.data === 'ok') {
                resetError();
                replaceToAccount();
            } else {
                setError("Wrong username or password");
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
            setError("Get user role error");
        })
    }
}



