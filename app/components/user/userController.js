var app = angular.module('web-gui-app.user')

.controller('userController', ["$scope", "Restangular", function ($scope, Restangular) {

  $scope.serviceFailures = new Array();

  $scope.save = function() {
    var baseUsers = Restangular.all('user');

    var success = function() {
      console.log("[DONE] POST /User");
    }

    var failure = function(response) {
      console.log("[FAILED] POST /user - [CODE] " + response.status);

      for (var i = 0; i < response.data.length; i++) {
        var field = response.data[i].field;
        var message = response.data[i].message;

        $scope.newUserForm[field].$setValidity(message, false);
        $scope.serviceFailures[field] = response.data[i];
      }
      console.log(response);
    }

    console.log("POST /user");
    $scope.serviceFailures = new Array();
    baseUsers.post($scope.user).then(success, failure);
  }

}]);
