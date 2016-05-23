var app = angular.module('web-gui-app.timeline', [])

.controller('timelineController', ["$scope", "posts", function ($scope, posts) {

  $scope.posts = posts;

}]);
