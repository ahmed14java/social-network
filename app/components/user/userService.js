var app = angular.module('web-gui-app.user')

.factory('userService', function() {
  var loggedUser;

  return {
   storeUserInSession: function(user) { loggedUser = user; },
   loggedUser: function() { return loggedUser; }
  }
})
