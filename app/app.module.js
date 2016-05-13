var app = angular.module('web-gui-app', [
  'ngComponentRouter',
  'ngMessages',
  'restangular',
  'web-gui-app.shared.headers',
  'web-gui-app.shared.directives',
  'web-gui-app.welcome',
  'web-gui-app.user',
  'web-gui-app.timeline',
])

.config(function($locationProvider) {
  $locationProvider.html5Mode(true);
})

.config(function(RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:50000');
})

.component('webGuiComponent', {
  template: '<ng-outlet></ng-outlet>',
  $routeConfig: [
    { path: '/welcome/...', name: 'Welcome', component: 'welcomeComponent', useAsDefault: true },
    { path: '/timeline/...', name: 'Timeline', component: 'timelineComponent' }
  ]
})

.value('$routerRootComponent', 'webGuiComponent');
