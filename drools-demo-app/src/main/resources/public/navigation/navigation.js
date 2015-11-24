(function() {
    
    var app = angular.module('ruleApp.navigationApp', ['ngRoute']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .otherwise( { redirectTo: '/subject-rating' } );

    });
    
}());
