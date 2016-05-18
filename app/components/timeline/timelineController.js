var app = angular.module('web-gui-app.timeline', [])

.controller('timelineController', ["$scope", "timelineService", function ($scope, timelineService) {

  $scope.posts = timelineService.posts();

}]);
