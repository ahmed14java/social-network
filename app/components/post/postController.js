var app = angular.module('web-gui-app.post', [])

.controller('postController', ["$scope", "timelineService", "userService", "postService",
  function ($scope, timelineService, userService, postService) {

  $scope.save = (post) => {
    var postToBeSaved = {};

    angular.copy(post, postToBeSaved);
    postToBeSaved.username = userService.loggedUser().username;

    var fnSuccess = () => {
      timelineService.push(postToBeSaved);
    }

    var fnFailure = (response) => {
      console.log("[FAILED] POST " + response.config.url
                + " [CODE] " + response.status, response.status);
    }

    postService.save(postToBeSaved, fnSuccess, fnFailure);
    angular.copy({}, post);
  }

}]);
