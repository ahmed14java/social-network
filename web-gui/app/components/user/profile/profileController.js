var app = angular.module('web-gui-app.user.profile', [])

.controller('profileController', ["user", "$scope", function (user, $scope) {

  $scope.user = user;

}]);
