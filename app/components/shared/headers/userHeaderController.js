var app = angular.module('web-gui-app.shared.headers')

.controller('userHeaderController', ["$scope", "currentUser", function ($scope, currentUser) {

  $scope.user = currentUser;

}]);
