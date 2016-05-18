var app = angular.module('web-gui-app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise("/");

  $stateProvider
    .state('welcome', {
      abstract: true,
      views: {
        '': {
          template:
            '<div ui-view="header"></div>' +
            '<div class="container">' +
              '<div ui-view="content"></div>' +
            '</div>'
        },
        'header@welcome': {
          templateUrl: '/app/components/shared/headers/vistantHeader.html',
          controller: 'visitantHeaderController'
        },
        'content@welcome': { template: '<div ui-view></div>' }
      }
    })
    .state('welcome.index', {
      url: '/',
      template: '<h1>Welcome I guess</h1>'
    })
    .state('welcome.newUser', {
      url: '/newUser',
      templateUrl: '/app/components/user/new.html',
      controller: 'userController'
    })
    .state('welcome.login', {
      url: '/login',
      templateUrl: '/app/components/user/login.html',
      controller: 'userController'
    });
});
