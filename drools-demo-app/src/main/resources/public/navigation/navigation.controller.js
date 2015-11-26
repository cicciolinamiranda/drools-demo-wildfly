(function() {

    var NavigationController = function($window, $cookies,
            AuthenticationEndpointService) {
        var _this = this;
        _this.logout = logout;
        _this.loggedUser = {
            token : $cookies.getObject('sessionId'),
            role : $cookies.getObject('role')
        };
        function logout() {
            AuthenticationEndpointService.logout();
            $window.location.href = '/';
        }
    };

    NavigationController.$inject = [ '$window', '$cookies',
            'AuthenticationEndpointService' ];

    angular.module('ruleApp.navigationApp').controller('NavigationController',
            NavigationController);

}());
