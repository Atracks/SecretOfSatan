var AppServices = angular.module('AngularSpringApp.services', []);

AppServices.value('version', '0.1');

AppServices.service('adminAccountService', ['$http','$q', function($http) {
    return {
        getUsers: function() {return $http.get('users/getUsers');},
        deleteUser: function(login) {return $http.delete('users/deleteUser/' + login)},
        letsRock: function() {return $http.patch('users/calculateTargets')}
    }
}]);

AppServices.service('userAccountService', ['$http','$q', function($http) {
    return {
        getUser: function(login) {
            return $http.get('users/getUser/' + login)
        },
        updateUser: function(user) {
            return $http.put('users/updateUser', user)
        }
    }
}]);

