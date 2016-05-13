var app = angular.module('web-gui-app.shared.headers')

.controller('userHeaderController', ["$scope", "userService", function ($scope, userService) {

  // this.$routerOnActivate = function(next) {
  //   console.log("shit never runs");
  //   $scope.user = userService.loggedUser();
  // };

  this.$routerCanReuse = function (next, prev) {
   console.log("striking out");
    };

    this.$routerOnActivate = (next) => {
      console.log("striking out");
    };

    this.$routerOnReuse = (next, prev) => {
      console.log("striking out");
    };

    this.hasAppLocationChanged = (next, prev) => {
      console.log("striking out");
    };

    this.changeAppLocation = (appLocation) => {
      console.log("striking out");
    };

    this.loadAppIntoIFrame = (appName, appLocation) => {
      console.log("striking out");
    };

}]);
