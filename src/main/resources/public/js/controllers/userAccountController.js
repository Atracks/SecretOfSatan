'use strict';

var userAccountController = function($scope, $http, userAccountService) {
    $scope.errorMessage = '';
    $scope.error = false;
    $scope.letterIsSent = false;
    $scope.targetIsAppointed = false;
    $scope.targetName = 'no_target';
    $scope.targetDesire = 'no_target_desire';
    getUser('terminator1')

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

    /*$scope.getTarget = function() {
        if ($scope.user.target.length > 0) {
            userAccountService.getUser($scope.user.target)
                .success(function(target) {
                    $scope.targetName = target.name;
                    $scope.targetDesire = target.desire;
                })
                .error(function() {
                    setError('target exist, but not available');
                });
        }
    }*/

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
