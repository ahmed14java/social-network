var app = angular.module('web-gui-app', [
  'ngMessages',
  'restangular',
  'ui.router',
  'web-gui-app.shared.headers',
  'web-gui-app.user'
])

.config(function(RestangularProvider) {
  RestangularProvider.setBaseUrl('http://localhost:50000');
})

.config(function($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise("/welcome/");

  $stateProvider
    .state('webGuiApp', {
      abstract: true,
      template:
      '<div ui-view="header"></div>' +
      '<div class="container">' +
        '<div ui-view="content"></div>' +
      '</div>'
    })
    .state('webGuiApp.welcome', {
      abstract: true,
      url: '/welcome',
      views: {
        'header': {
          templateUrl: '/app/components/shared/headers/vistantHeader.html',
          controller: 'visitantHeaderController'
        },
        'content': { template: '<div ui-view></div>' }
      }
    })
    .state('webGuiApp.welcome.index', {
      url: '/',
      template: '<h1>Welcome I guess</h1>'
    })
    .state('webGuiApp.welcome.newUser', {
      url: '/newUser',
      templateUrl: '/app/components/user/new.html',
      controller: 'userController'
    })
    .state('webGuiApp.home', {
      abstract: true,
      url: '/home',
      views: {
        'header': {
          templateUrl: '/app/components/shared/headers/userHeader.html',
          controller: 'userHeaderController'
        },
        'content': { template: '<div ui-view></div>' }
      }
    })
    .state('webGuiApp.home.timeline', {
      url: '/',
      templateUrl: '/app/components/timeline/index.html'
    });
});
