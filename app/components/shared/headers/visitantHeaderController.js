var app = angular.module('web-gui-app.shared.headers', [])

.controller('visitantHeaderController', ["$scope", "$state", function ($scope, $state) {

  $scope.goTo = dest => { $state.go(dest); }

}]);
