var app = angular.module('web-gui-app.shared.header', [])

.controller('headerController', ["$scope", "$state", function ($scope, $state) {

  $scope.goTo = function(destState) { $state.go(destState); }

}]);
