var app = angular.module('web-gui-app.post')

.factory('postService', ["postServiceClient", function(postServiceClient) {

  return {
   save: (post, fnSuccess, fnFailure) => {
     postServiceClient
      .one('user', post.username)
      .post('post', post)
      .then(fnSuccess, fnFailure);
   }
  }
}])
