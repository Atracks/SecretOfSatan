var AppServices = angular.module('AngularSpringApp.services', []);

AppServices.value('version', '0.1');

AppServices.service('adminAccountService', ['$http','$q', function($http) {
    return {
        getUsers: function () {return $http.get('users/getUsers');},
        deleteUser: function (login) {return $http.delete('users/deleteUser/' + login)},
        letsRock: function() {return $http.put('users/calculateTargets')}
    }
}]);

AppServices.service('userAccountService', ['$http','$q', function($http) {
    return {
        getUser: function(login) {
            return $http.get('users/getUser/' + login)
        },

        updateUser: function(user) {
            return $http.put('users/updateUser', user)
        },

        getCurrentUserLogin: function() {
            return $http.get('users/getCurrentUserLogin/')
        },

        logout: function () {
            return $http.post('/logout')
        }
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

AppServices.service('newUserService', ['$http', function ($http) {
    return {
        createUser: function (login, casualName, password) {
            var userDao = {
                login: login,
                password: password,
                name: casualName,
                desire: '',
                targetLogin: ''
            }
            return $http.post('users/addUser', userDao);
        }
    }

}])




