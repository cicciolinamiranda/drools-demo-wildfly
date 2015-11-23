(function() {
    
    var LoginController = function ($scope,$http,$window,LoginEndpointService,AuthenticationEndpointService) {

        $scope.loginEmail = undefined;
        $scope.password = undefined;
        $scope.loginUser = loginUser;

        $scope.showError = false;

        function loginUser() {
        	alert('before endpoint login');
        	LoginEndpointService.login($scope.loginEmail, $scope.password).then(function (response) {
              if (response.data) {
            	  AuthenticationEndpointService.auth(response.data.id);
                redirectToHome(true);
              } else {
            	  AuthenticationEndpointService.logout();
                redirectToHome(false);
              }
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

	LoginController.$inject = ['$scope','$http','$window','LoginEndpointService','AuthenticationEndpointService'];

    angular.module('ruleApp.loginApp')
      .controller('LoginController', LoginController);

}());

