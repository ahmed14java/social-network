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
          controller: 'userHeaderController'
        },
        'content@home': { template: '<div ui-view></div>' }
      },
      resolve: {
        currentUser: ["userService", "$state", function(userService, $state) {
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
      url: '/{profileUsername}',
      views: {
        '': {
          templateUrl: '/app/components/user/profile/profile.html',
          controller: 'profileController',
        },
        'timeline@home.profile': {
          templateUrl: '/app/components/timeline/timeline.html',
          controller: 'timelineController',
          resolve: {
            posts: ["$stateParams", "postService", function($stateParams, postService) {

              var fnSuccess = (retrievedPosts) => { console.log(retrievedPosts); return retrievedPosts; }
              var fnFailure = () => { console.log("it didn't work!"); }

              return postService.retrievePostsOf($stateParams.profileUsername, fnSuccess, fnFailure);
            }]
          }
        }
      }
    });
});
