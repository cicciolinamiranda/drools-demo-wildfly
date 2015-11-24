'use strict';

// Declare app level module which depends on views, and components
angular.module('ruleApp', [
  'ngRoute',
  'ngCookies',
  'ruleApp.admin',
  'ruleApp.subjectRatingApp',
  'ruleApp.authenticationApp',
  'ruleApp.navigationApp'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/subject-rating'});

}]);


