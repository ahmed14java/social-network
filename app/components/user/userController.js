var app = angular.module('web-gui-app.user', [])

.controller('userController', ["$scope", "$state", "userService", function ($scope, $state, userService) {

    $scope.save = function() {

      var fnSuccess = function() {
        userService.storeUserInSession($scope.user);
        $state.go('home.index');
      }

      var fnFailure = function(response) {
        console.log("[FAILED] POST /user - [CODE] " + response.status, response.status);

        for (var i = 0; i < response.data.length; i++) {
          var field = response.data[i].field;
          var message = response.data[i].message;

          $scope.newUserForm[field].$setValidity(message, false);
        }
      }

      userService.save($scope.user, fnSuccess, fnFailure);
    }

}]);
