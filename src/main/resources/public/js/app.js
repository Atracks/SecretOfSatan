'use strict';

var AngularSpringApp = {};

var App = angular.module('AngularSpringApp', ['AngularSpringApp.filters', 'AngularSpringApp.services', 'AngularSpringApp.directives']);

App.config(['$routeProvider', function ($routeProvider) {
  /*$routeProvider.when('/login', {
    templateUrl: 'login/loginLayout.html',
    controller: loginController
  });

  $routeProvider.when('/account', {
    templateUrl: 'account/accountLayout.html',
    controller: accountController
  });

  $routeProvider.when('/account-update', {
    templateUrl: 'account/accountUpdateLayout.html',
    controller: accountUpdateController
  });*/

  $routeProvider.when('/admin-account', {
    templateUrl: 'account/adminAccountLayout.html',
    controller: adminAccountController
  });

  $routeProvider.when('/user-account', {
      templateUrl: 'account/userAccountLayout.html',
      controller: userAccountController
  });

  $routeProvider.otherwise({redirectTo: '/user-account'});
}]);


