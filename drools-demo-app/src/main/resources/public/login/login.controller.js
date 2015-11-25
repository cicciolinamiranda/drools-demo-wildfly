(function() {
    
    var LoginController = function ($scope,$http,$window,$location,LoginEndpointService,AuthenticationEndpointService) {

        $scope.loginEmail = undefined;
        $scope.password = undefined;
        $scope.loginUser = loginUser;
        $scope.app = {}
        $scope.app.TOKEN = null;
        $scope.app.ROLE = null
        $scope.showError = false;

        function loginUser() {
            var params = {
                "username" : $scope.loginEmail,
                "password" : btoa($scope.password)
            }
            $http({
                method: "POST",
                url : "/authenticate",
                data : params
            })
                .success(function(response) {
                    alert(response.token)
                    $scope.app.TOKEN = response.token
                    $scope.app.ROLE = response.role
                    console.log($scope.app.ROLE);
                    console.log($scope.app.TOKEN);
                    $location.path("/subject-rating")
                });

          }

        function getParameterByName(name) {
          name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
          var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec($window.location.href);
          return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        function redirectToHome(stat) {
          if (stat) {
            $window.location.href = $location.protocol() + "://" + location.host + "/#" + getParameterByName('next');
          } else {
            $scope.loginFailed = true;
          }
        }
	};

	LoginController.$inject = ['$scope','$http','$window','$location','LoginEndpointService','AuthenticationEndpointService'];

    angular.module('ruleApp.loginApp')
      .controller('LoginController', LoginController);

}());

