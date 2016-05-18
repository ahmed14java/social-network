var app = angular.module('web-gui-app.post', [])

.controller('postController', ["$scope", "timelineService", "userService", function ($scope, timelineService, userService) {

  $scope.save = () => {
    var post = {};

    post.content = $scope.post.content;
    post.username = userService.loggedUser().username;

    timelineService.push(post);
  }

}]);
