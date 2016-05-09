var app = angular.module('web-gui-app.user')

.controller('userController', ["$scope", "Restangular", function ($scope, Restangular) {

  $scope.save = function() {
    var baseUsers = Restangular.all('user');

    console.log("postando...");
    console.log($scope.user);
    baseUsers.post($scope.user);
    console.log("postado...");
  }
}]);
