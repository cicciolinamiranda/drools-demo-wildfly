(function() {
    
    var app = angular.module('ruleApp.admin', ['ngRoute', 'ngCookies']);
    
    app.config(function($routeProvider, $httpProvider) {
        $routeProvider
            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'admin/admin.html'
            })
            .otherwise( { redirectTo: '/' } );
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        $httpProvider.interceptors.push('APIInterceptor');
    });
    
}());
