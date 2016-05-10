var app = angular.module('web-gui-app', [
  'ui.router',
  'ngMessages',
  'restangular',
  'web-gui-app.shared.header',
  'web-gui-app.user'
])

.config(function($locationProvider) {
  $locationProvider.html5Mode(true);
})

.config(function(RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:50000');
})

.config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise("/home.welcome");

  $stateProvider
    .state('home', {
      abstract: true,
      views: {
       "header": {
         templateUrl: "/app/components/shared/header/header.html",
         controller: "headerController"
       },
       "main": {
         template: "<div class=\"container\"><div ui-view></div></div>"
       }
      }
    })
    .state('home.welcome', {
      url: "/",
      template: "Welcome bro!"
    })
    .state('home.newUser', {
      url: "/user/new",
      templateUrl: "/app/components/user/new.html",
      controller: "userController"
    });

});
