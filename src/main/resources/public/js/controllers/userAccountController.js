'use strict';

var userAccountController = function($scope, $http, userAccountService) {
    $scope.errorMessage = '';
    $scope.error = false;
    getUser('terminator')

    function getUser(login) {
        userAccountService.getUser(login)
            .success(function (user) {
                $scope.user = user;
            })
            .error(function () {
                setError('unable to get user');
            });
    }

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
