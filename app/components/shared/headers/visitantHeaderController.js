var app = angular.module('web-gui-app.shared.headers')

.controller('visitantHeaderController', ["$scope", "$location", function ($scope, $location) {

  $scope.goTo = function(dest) { console.log("GOING TO"); $location.href = dest; }

}]);
