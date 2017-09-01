var app = angular.module('web-gui-app.timeline', [])

.controller('timelineController', ["$scope", "posts", "user", function ($scope, posts, user) {

  $scope.posts = posts;
  $scope.user = user;

}]);
