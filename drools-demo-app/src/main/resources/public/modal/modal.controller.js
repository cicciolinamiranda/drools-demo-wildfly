(function() {

    var ModalController = function($scope, close) {


    };

    ModalController.$inject = ['$scope', 'selectedTrack', 'ngAudio'];

    angular.module('ruleApp.modal')
      .controller('ModalController', ModalController);


}());