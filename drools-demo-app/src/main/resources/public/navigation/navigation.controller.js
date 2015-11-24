(function() {

    var NavigationController = function($window, AuthenticationEndpointService) {
        var _this = this;
        _this.logout = logout;

        function logout() {
            AuthenticationEndpointService.logout();
            $window.location.href = '/';
        }
    };

    NavigationController.$inject = [ '$window', 'AuthenticationEndpointService' ];

    angular.module('ruleApp.navigationApp').controller('NavigationController',
            NavigationController);

}());
