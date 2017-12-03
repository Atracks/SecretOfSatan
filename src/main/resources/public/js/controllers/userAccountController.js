'use strict';

var userAccountController = function($scope, $http, userAccountService) {
    $scope.errorMessage = '';
    $scope.error = false;
    $scope.letterIsSent = false;
    $scope.targetIsAppointed = false;
    $scope.targetName = 'no_target';
    $scope.targetDesire = 'no_target_desire';
    $scope.currentUserLogin = getCurrentUserLogin();
    getUser($scope.currentUserLogin)

    function getCurrentUserLogin() {
        userAccountService.getCurrentUserLogin();
    }

    function getUser(login) {
        userAccountService.getUser(login)
            .success(function(user) {
                $scope.user = user;
                if (user.target.length > 0) {
                    $scope.targetIsAppointed = true;
                    getTarget(user.target);
                }
            })
            .error(function() {
                setError('unable to get user ' + login);
            });
    }

    function getTarget(targetLogin) {
        userAccountService.getUser(targetLogin)
            .success(function(targetUser) {
                $scope.targetName = targetUser.name;
                $scope.targetDesire = targetUser.desire;
            })
            .error(function() {
                setError('strange shit: target is appointed, but not available');
            });
    }

    $scope.updateUser = function(user) {
        userAccountService.updateUser(user)
            .success(function() {
                getUser($scope.user.login);
                $scope.letterIsSent = true;
            })
            .error(function() {
                setError('unable to update user ' + $scope.user);
            });
    }

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
