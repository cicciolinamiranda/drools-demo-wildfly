
var app = angular.module('ruleApp', []);
app.controller('ruleController', function($scope, $http) {
    $scope.packagename = "";
    $scope.version = "";
    $http.get("http://localhost:8081/admin/rules/list")
    .success(function(response) {
      console.log(response.listRuleArtifactResource);
    	$scope.packages = response.listRuleArtifactResource;
    	$scope.loopCount = 0;
    });
});
