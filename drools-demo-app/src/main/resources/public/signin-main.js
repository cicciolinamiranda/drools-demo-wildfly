'use strict';

// Declare app level module which depends on views, and components
angular.module('ruleApp', [
  'ngRoute',
  'ruleApp.loginApp'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/'});

}]);


