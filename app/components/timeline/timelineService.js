var app = angular.module('web-gui-app.timeline')

.factory('timelineService', function () {

  var posts = [];

  return {
   push: post => { posts.push(post); },
   posts: () => { return posts; }
  }
});
