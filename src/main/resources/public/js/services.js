var AppServices = angular.module('AngularSpringApp.services', []);

AppServices.value('version', '0.1');

AppServices.service('adminAccountService', ['$http','$q', function($http) {
    return {
        getUsers: function () {return $http.get('users/getUsers');},
        deleteUser: function (login) {return $http.delete('users/deleteUser/' + login)},
        letsRock: function() {return $http.patch('users/calculateTargets')}
    }
}]);

AppServices.service('loginService', ['$http','$q', function($http) {
    return {
        login: function (username, password) {
            var postData = 'username=' + username + '&password=' + password;
            return $http({
                method: 'POST',
                url: '/authenticate',
                data: postData,
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "X-Login-Ajax-call": 'true'
                }
            })
        },

        getCurrentUserRole: function () {return $http.get('users/currentUserRole')}
    }
}]);




