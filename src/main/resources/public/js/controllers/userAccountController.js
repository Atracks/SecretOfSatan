'use strict';

var userAccountController = function($scope, $http, userAccountService) {
    $scope.errorMessage = '';
    $scope.error = false;
    $scope.letterIsSent = false;
    $scope.targetIsAppointed = false;
    $scope.targetName = 'no_target';
    $scope.targetDesire = 'no_target_desire';
    $scope.currentUserLogin = getCurrentUserLogin();

    function getCurrentUserLogin() {
        userAccountService.getCurrentUserLogin()
            .success(function (login) {
                getUser(login);
            })
            .error(function () {
                setError('Не получилось определить никнейм')
            });
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
                setError('Не получилось тебя опознать ' + login);
            });
    }

    function getTarget(targetLogin) {
        userAccountService.getUser(targetLogin)
            .success(function(targetUser) {
                $scope.targetName = targetUser.name;
                $scope.targetDesire = targetUser.desire;
            })
            .error(function() {
                setError('Мешок мне в трубу! Твой аккаунт почему-то недоступен');
            });
    }

    $scope.updateUser = function(user) {
        userAccountService.updateUser(user)
            .success(function() {
                getUser($scope.user.login);
                $scope.letterIsSent = true;
                window.location.replace("#/send-letter-success");
            })
            .error(function() {
                setError('Не полчилось обновить информацию ' + $scope.user);
            });
    }

    $scope.logout = function() {
        userAccountService.logout()
            .error(function() {
                setError('Не получилось выйти');
            });
    }

    function setError (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
}
