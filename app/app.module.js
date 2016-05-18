var app = angular.module('web-gui-app', [
  'ngMessages',
  'restangular',
  'ui.router',
  'ui.bootstrap',
  'web-gui-app.shared.headers',
  'web-gui-app.shared.directives',
  'web-gui-app.routes',
  'web-gui-app.user',
  'web-gui-app.post',
  'web-gui-app.timeline'
])

.config(function(RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:50000');
})

.run(function($rootScope, $state) {
  $rootScope.$state = $state;
  function message(to, toP, from, fromP) { return from.name  + angular.toJson(fromP) + " -> " +     to.name + angular.toJson(toP); }
  $rootScope.$on("$stateChangeStart", function(evt, to, toP, from, fromP) { console.log("Start:   " + message(to, toP, from, fromP)); });
  $rootScope.$on("$stateChangeSuccess", function(evt, to, toP, from, fromP) { console.log("Success: " + message(to, toP, from, fromP)); });
  $rootScope.$on("$stateChangeError", function(evt, to, toP, from, fromP, err) { console.log("Error:   " + message(to, toP, from, fromP), err); });
})
