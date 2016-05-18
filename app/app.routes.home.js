var app = angular.module('web-gui-app.routes')

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
    .state('home', {
      abstract: true,
      views: {
        '': {
          template:
            '<div ui-view="header"></div>' +
            '<div class="container">' +
              '<div ui-view="content"></div>' +
            '</div>'
        },
        'header@home': {
          templateUrl: '/app/components/shared/headers/userHeader.html',
          controller: 'userHeaderController',
          resolve: {
            currentUser: ["userService", "$state", function(userService, $state) {
              user = userService.loggedUser();
              // if (user == undefined) $state.go("welcome.index");
              // else return user;
              return user;
            }]
          }
        },
        'content@home': { template: '<div ui-view></div>' }
      }
    })
    .state('home.index', {
      url: '/home',
      views: {
        '': {
          templateUrl: '/app/components/home/home.html'
        },
        'userSummary@home.index': {
          templateUrl: '/app/components/user/userSummary.html'
        },
        'timeline@home.index': {
          templateUrl: '/app/components/timeline/timeline.html',
          controller: 'timelineController'
        },
        'quickPost@home.index': {
          templateUrl: '/app/components/post/quickPost.html',
          controller: 'postController'
        }
      }
    });
});
