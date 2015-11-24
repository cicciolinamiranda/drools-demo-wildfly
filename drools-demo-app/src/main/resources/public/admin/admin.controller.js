(function() {
    
    var AdminController = function ($scope,$route,$window,$http) {
        $scope.newArtifact = {
                groupId : "",
                artifactId : "",
                version : ""
            };
            $scope.packages = [];
            $scope.itemsByPage = 15;

            // notification attributes
            $scope.notifValue = '';
            $scope.showError = false;

            // functions
            $scope.sendData = sendData;
            $scope.clear = clear;
            $scope.removeArtifact = removeArtifact;
            $scope.activate = activate;

            $http.get("/admin/rules/list").success(
                    function(response) {
                        $scope.packages = response.listRuleArtifactResource;
                        $scope.loopCount = 0;
                    })

            function sendData() {
                $http.post("/admin/rules/add", $scope.newArtifact)
                        .success(function(data, status) {
                        	console.log(status);
                            $window.location.reload();
                        }).error(
                                function(response) {
                                    $scope.notifValue = 'Artifact with the given version is already existing.';
                                    $scope.showError = true;
                                });

            }
            function clear() {
                $scope.newArtifact = {
                    groupId : "",
                    artifactId : "",
                    version : ""
                };
            }

            function removeArtifact(index, data) {
                    var artifactActivationResource = {
                        id : data.id
                    }

                    // remove the row specified in index
                    $scope.packages.splice(index, 1);
                    // if no rows left in the array create a blank array
                    if ($scope.packages.length === 0) {
                        $scope.packages = [];
                    }
                    $http.post("/admin/rules/delete",
                            artifactActivationResource).success(function(data, status) {
                    })

            }

            function activate(data) {
                var ruleArtifactResource = {
                    id : data.id
                }
                $http.post("/admin/rules/activate",
                        ruleArtifactResource).success(function(data, status) {
                            $window.location.reload();
                }).error(
                        function(response) {
                            $scope.notifValue = 'Artifact is not existing on the workbench.Choose other artifact available or upload selected on the workbench';
                            $scope.showError = true;
                        });
            }

	};

    AdminController.$inject = ['$scope','$route','$window','$http'];

    angular.module('ruleApp.admin')
      .controller('AdminController', AdminController);

}());