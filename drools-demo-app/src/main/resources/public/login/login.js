(function() {
    
    var app = angular.module('ruleApp.loginApp', ['ngRoute']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                controller: 'LoginController',
                templateUrl: 'login/login.html'
            })
            .otherwise( { redirectTo: '/' } );

    });
    
}());
