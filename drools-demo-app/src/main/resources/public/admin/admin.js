(function() {
    
    var app = angular.module('ruleApp.admin', ['ngRoute']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .when('/admin', {
                controller: 'AdminController',
                templateUrl: 'admin/admin.html'
            })
            .otherwise( { redirectTo: '/' } );

    });
    
}());
