var app = angular.module('web-gui-app.user')

.factory('userService', function() {
  var loggedUser;

  return {
   storeUserInSession: function(user) {
      console.log("storing user in session " + user.username);
      loggedUser = user;
    },
    loggedUser: function() { console.log("returning " + user.username); return loggedUser; }
  }
})
