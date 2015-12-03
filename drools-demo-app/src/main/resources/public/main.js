'use strict';

// Declare app level module which depends on views, and components
angular.module('ruleApp', [
  'ngRoute',
  'ngCookies',
  'ruleApp.admin',
  'ruleApp.subjectRatingApp',
  'ruleApp.authenticationApp',
  'ruleApp.navigationApp',
    'ruleApp.modal'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/subject-rating'});

}])
.service('APIInterceptor', function($rootScope, AuthenticationEndpointService) {
  var service = this;

  service.request = function(config) {
    var token = AuthenticationEndpointService.getId(),
        token = token ? token : null;

    if (token) {
      config.headers['X-AUTH-TOKEN'] = token;
    }
    return config;
  };

  //service.responseError = function(response, ModalService) {
  //  if (response.status === 403) {
  //    ModalService.showModal({
  //      templateUrl: 'modal/modal.html',
  //      controller: "ModalController",
  //
  //    }).then(function(modal) {
  //      modal.element.modal();
  //      modal.close.then(function(result) {
  //      });
  //    });    }
  //  return response;
  //};
});
