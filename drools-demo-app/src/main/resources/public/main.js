
var app = angular.module('ruleApp', []);
app.controller('ruleController', function($scope, $http,$window) {
    $scope.newArtifact = {
    		groupId: "",
    		artifactId: "",
    		version: ""
    };
    $scope.itemsByPage=15;

    //functions
    $scope.sendData = sendData;
    $scope.clear = clear;
    $scope.removeArtifact = removeArtifact;
    $http.get("http://localhost:8081/admin/rules/list")
    .success(function(response) {
    	$scope.packages = response.listRuleArtifactResource;
    	$scope.loopCount = 0;
    });
    
    function sendData() {
    	console.log("groupId: "+$scope.newArtifact);
        $http.post("http://localhost:8081/admin/rules/add", $scope.newArtifact).success(function(data, status) {
            $window.location.reload();
        })

    }
    function clear() {
        $scope.newArtifact = {
        		groupId: "",
        		artifactId: "",
        		version: ""
        };
    }

    function removeArtifact(index,data){
    	console.log("index: "+index);
        console.log("id: "+data.id);
        var data = {
        		"id": data.id
        }
        // remove the row specified in index
    	$scope.packages.splice( index, 1);
        // if no rows left in the array create a blank array
        if ($scope.packages.length === 0){
        	$scope.packages = [];
        }
        $http.delete("http://localhost:8081/admin/rules/delete", data).success(function(data, status) {
            $window.location.reload();
        })
      }
});
