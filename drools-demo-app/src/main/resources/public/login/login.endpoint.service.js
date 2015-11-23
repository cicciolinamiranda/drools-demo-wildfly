(function() {

    var LoginEndpointService = function($q) {

    	  var services = {};
          services.login = function(email, pass) {
        	  alert('servicelogin');
              return $q.when({data: {id: 1231}});
          };
          return services;
    };

    LoginEndpointService.$inject = [ '$q' ];

    angular.module('ruleApp.loginApp').service('LoginEndpointService',
            LoginEndpointService);

}());
