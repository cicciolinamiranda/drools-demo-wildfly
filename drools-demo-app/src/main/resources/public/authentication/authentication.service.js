(function() {

    var AuthenticationEndpointService = function($window, $cookies) {

        var services = {};
        services.auth = function(token) {
            $cookies.putObject('sessionId', token);
        };

        services.logout = function() {
            $cookies.remove('sessionId');
        };

        services.isAuthenticated = function() {
            if ($cookies.getObject('sessionId')) {
                return true;
            } else {
                return false;
            }
        };

        services.getId = function() {
            return $cookies.getObject('sessionId');
        };
        return services;
    };

    AuthenticationEndpointService.$inject = [ '$window', '$cookies' ];

    angular.module('ruleApp.authenticationApp').service(
            'AuthenticationEndpointService', AuthenticationEndpointService);

}());
