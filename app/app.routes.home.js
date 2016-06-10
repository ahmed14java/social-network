var app = angular.module('web-gui-app.routes')

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
    .state('home', {
      abstract: true,
      views: {
        '': {
          template:
            '<div class="bg-background">' +
            ' <div ui-view="header"></div>' +
            ' <div class="container">' +
            '  <div ui-view="content"></div>' +
            ' </div>' +
            '</div>'
        },
        'header@home': {
          templateUrl: '/app/components/shared/headers/userHeader.html',
          controller: 'userHeaderController'
        },
        'content@home': { template: '<div ui-view></div>' }
      },
      resolve: {
        user: ["userService", "$state", function(userService, $state) {
          user = userService.loggedUser();
          if (user == undefined) $state.go("welcome.index");
          else return user;
        }]
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
          controller: 'timelineController',
          resolve: {
            posts: ["timelineService", function (timelineService) {
              return timelineService.posts();
            }]
          }
        },
        'quickPost@home.index': {
          templateUrl: '/app/components/post/quickPost.html',
          controller: 'postController'
        }
      }
    })
    .state('home.profile', {
      url: '/{username}',
      views: {
        '': {
          templateUrl: '/app/components/user/profile/profile.html',
          controller: 'profileController'
        },
        'timeline@home.profile': {
          templateUrl: '/app/components/timeline/timeline.html',
          controller: 'timelineController',
          resolve: {
            posts: ["$stateParams", "postService", function($stateParams, postService) {

              var fnSuccess = (retrievedPosts) => { return retrievedPosts; }
              var fnFailure = () => { console.log("it didn't work!"); }

              return postService.retrievePostsOf($stateParams.username, fnSuccess, fnFailure);
            }]
          }
        }
      },
      resolve: {
        user: ["$stateParams", "userService", function($stateParams, userService) {

          var fnSuccess = (response) => { return response; }
          var fnFailure = (response) => { console.log(response); }

          return userService.findByUsername($stateParams.username, fnSuccess, fnFailure);
        }]
      }
    });
});
