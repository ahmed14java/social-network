var app = angular.module('web-gui-app.user')

.factory('userService', ["Restangular", function(Restangular) {

  var baseUsers = Restangular.all('user');
  var loggedUser;

  return {
   storeUserInSession: user => { loggedUser = user; },
   loggedUser: () => { return loggedUser; },
   save: (user, fnSuccess, fnFailure) => {
     console.log("POST /user -> " + user.username);
     baseUsers.post(user).then(fnSuccess, fnFailure);
     console.log("[DONE] POST /User -> " + user.username);
   }
  }
}])
