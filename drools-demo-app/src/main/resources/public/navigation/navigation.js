(function() {
    
    var app = angular.module('ruleApp.navigationApp', ['ngRoute','ngCookies']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .otherwise( { redirectTo: '/subject-rating' } );

    });
    
}());
