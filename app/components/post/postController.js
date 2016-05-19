var app = angular.module('web-gui-app.post', [])

.controller('postController', ["$scope", "timelineService", "userService", function ($scope, timelineService, userService) {

  $scope.save = (postToBeSaved) => {
    var post = {};

    angular.copy(postToBeSaved, post);
    post.username = userService.loggedUser().username;

    timelineService.push(post);

    angular.copy({}, postToBeSaved);
  }

}]);
