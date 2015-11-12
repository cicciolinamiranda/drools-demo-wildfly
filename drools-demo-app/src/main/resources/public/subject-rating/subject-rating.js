(function() {
    
    var app = angular.module('ruleApp.subjectRatingApp', ['ngRoute']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .when('/subject-rating', {
                controller: 'SubjectRatingController as vm',
                templateUrl: 'subject-rating/subject-rating.html'
            })
            .otherwise( { redirectTo: '/' } );

    });
    
}());