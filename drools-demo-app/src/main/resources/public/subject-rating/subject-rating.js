(function() {
    
    var app = angular.module('ruleApp.subjectRatingApp', ['ngRoute']);
    
    app.config(function($routeProvider, $httpProvider) {
        $routeProvider
            .when('/subject-rating', {
                controller: 'SubjectRatingController',
                templateUrl: 'subject-rating/subject-rating.html'
            })
            .otherwise( { redirectTo: '/' } );
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });
    
}());
