var app = angular.module('web-gui-app', [
  'ngComponentRouter',
  'ngMessages',
  'restangular',
  'web-gui-app.shared.header',
  'web-gui-app.user'
])

.config(function($locationProvider) {
  $locationProvider.html5Mode(true);
})

.config(function(RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:5000');
})

.component('mainContent', {
  template: '<div class="container"><ng-outlet></ng-outlet></div>',
  $routeConfig: [
    { path: '/user/...', name: 'User', component: 'user', useAsDefault: true }
  ]
})

.value('$routerRootComponent', 'mainContent');
