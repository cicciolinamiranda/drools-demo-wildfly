(function() {

    var AuthenticationEndpointService = function($window, $cookies) {

        var services = {};
        services.auth = function(token) {
            $cookies.putObject('sessionId', token.email);
            $cookies.putObject('admin', token.admin);
        };

        services.logout = function() {
            $cookies.remove('sessionId');
            $cookies.remove('admin');
        };

        services.isAuthenticated = function() {
            if ($cookies.getObject('sessionId')) {
                return true;
              } else {
                return false;
              }
      
        return services;
    };

    AuthenticationEndpointService.$inject = [ '$window', '$cookies' ];

    angular.module('ruleLoginApp.AuthenticationApp').service(
            'AuthenticationEndpointService', AuthenticationEndpointService);

}());
