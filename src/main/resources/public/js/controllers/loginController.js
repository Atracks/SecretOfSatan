'use strict';

var loginController = function($scope, $http, loginService) {
    $scope.error = false;
    $scope.errorMessage = '';

    $scope.onLogin = function () {
        loginService.login($scope.login, $scope.password).then(function (response) {
            if (response.data === 'ok') {
                $scope.error = false;
                replaceToAccount();
            } else {
                $scope.error = true;
                $scope.errorMessage = "Wrong username or password"
            }
        })
    };

    function replaceToAccount() {
        loginService.getCurrentUserRole().success(function (currentUserRole) {
            if(currentUserRole[0].authority === "ROLE_ADMIN") {
                window.location.replace('#/admin-account');
            }
            if(currentUserRole[0].authority === "ROLE_USER") {
                window.location.replace('#/user-account');
            }
            $scope.error = false;
        }).error(function () {
            $scope.error = true;
            $scope.errorMessage = 'Get user role error';
        })
    }
}

