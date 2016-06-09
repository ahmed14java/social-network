var app = angular.module('web-gui-app.timeline', [])

.controller('timelineController', ["$scope", "posts", "currentUser", function ($scope, posts, currentUser) {

  $scope.posts = posts;
  $scope.user = currentUser;

}]);
