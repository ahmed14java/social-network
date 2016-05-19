var app = angular.module('web-gui-app.post')

.factory('postService', ["postServiceClient", function(postServiceClient) {

  var basePost = postServiceClient.all('user');

  return {
   storeUserInSession: user => { loggedUser = user; }
  }
}])
