var app = angular.module('web-gui-app.user', [])

.controller('userController', ["$scope", "$state", "userService", "Upload", function ($scope, $state, userService, Upload) {

    $scope.save = function() {
      if ($scope.profilePic != undefined) uploadImageAndSaveUser();
      else saveUser();
    }

    function uploadImageAndSaveUser() {
      $scope.profilePic.upload = Upload.upload({
        url: 'http://localhost:50003/image',
        data: {image: $scope.profilePic},
      }).then(function (resp) {
          console.log('Img Key = ' + resp.data['key']);
          $scope.user.profilePicKey = resp.data['key'];
          saveUser();
      }, function (resp) {
          console.log('Error status: ' + resp.status);
      });
    }

    var fnAuthSuccess = function() {
      userService.storeUserInSession($scope.user);
      $state.go('home.index');
    }

    function saveUser() {
      var fnFailure = function(response) {
        console.log("[FAILED] POST /user - [CODE] " + response.status, response.status);

        for (var i = 0; i < response.data.length; i++) {
          var field = response.data[i].field;
          var message = response.data[i].message;

          $scope.newUserForm[field].$setValidity(message, false);
        }
      }

      userService.save($scope.user, fnAuthSuccess, fnFailure);
    }

    $scope.login = function() {
      var fnFailure = function(response) {
        console.log("[FAILED] POST /user - [CODE] " + response.status);
      }

      userService.authenticate($scope.user, fnAuthSuccess, fnFailure);
    }

}]);
