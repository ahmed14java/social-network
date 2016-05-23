var app = angular.module('web-gui-app.user.profile', [])

.controller('profileController', ["$stateParams", "$scope", function ($stateParams, $scope) {

  $scope.profileUsername = $stateParams.profileUsername;

}]);
