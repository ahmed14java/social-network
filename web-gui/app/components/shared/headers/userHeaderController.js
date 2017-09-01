var app = angular.module('web-gui-app.shared.headers')

.controller('userHeaderController', ["$scope", "user", function ($scope, user) {

  $scope.user = user;

}]);
