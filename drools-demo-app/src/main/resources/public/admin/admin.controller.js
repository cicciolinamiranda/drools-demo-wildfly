(function() {

    var AdminController = function($scope, $route, $window, $http, $cookies) {
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

        $http.get("/administration/rules/list").success(function(response) {
            $scope.packages = response.listRuleArtifactResource;
            $scope.loopCount = 0;
        })

        function sendData() {
            $http
                    .post("/administration/rules/add", $scope.newArtifact)
                    .success(function(data, status) {
                        console.log(status);
                        $window.location.reload();
                    })
                    .error(
                            function(response) {
                                $scope.showError = true;
                                if (response.status == 404) {
                                    $scope.notifValue = 'Artifact with the given version is already existing.';
                                } else if (response.status == 403) {
                                    $scope.notifValue = 'You are not authorize to add an artifact';
                                }
                            });

        }
        function clear() {5
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

            $http
                    .post("/administration/rules/delete", artifactActivationResource)
                    .success(function(data, status) {
                        $scope.packages.splice(index, 1);
                        // if no rows left in the array create a blank array
                        if ($scope.packages.length === 0) {
                            $scope.packages = [];
                        }
                    })
                    .error(
                            function(response) {
                                $scope.notifValue = 'You are not authorized to delete this artifact.';
                                $scope.showError = true;
                            });

        }

        function activate(data) {
            var ruleArtifactResource = {
                id : data.id
            }
            $http
                    .post("/administration/rules/activate", ruleArtifactResource)
                    .success(function(data, status) {
                        $window.location.reload();
                    })
                    .error(
                            function(response) {
                                $scope.showError = true;
                                if (response.status == 500) {
                                    $scope.notifValue = 'Artifact is not existing on the workbench.Choose other artifact available or upload selected on the workbench';

                                }  else if (response.status == 403) {
                                    $scope.notifValue = 'You are not authorize to activate an artifact';

                                }
                            });
        }

    };

    AdminController.$inject = [ '$scope', '$route', '$window', '$http',
            '$cookies' ];

    angular.module('ruleApp.admin').controller('AdminController',
            AdminController);

}());