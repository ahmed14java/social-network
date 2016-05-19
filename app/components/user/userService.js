var app = angular.module('web-gui-app.user')

.factory('userService', ["userServiceClient", function(userServiceClient) {

  var baseUsers = userServiceClient.all('user');
  var loggedUser;

  return {
   storeUserInSession: user => { loggedUser = user; },
   loggedUser: () => { return loggedUser; },
   save: (user, fnSuccess, fnFailure) => {
     console.log("POST /user -> " + user.username);
     baseUsers.post(user).then(fnSuccess, fnFailure);
     console.log("[DONE] POST /User -> " + user.username);
   },
   authenticate: (user, fnSuccess, fnFailure) => {
     console.log("GET /user -> " + user.username);
     baseUsers.get("", {
       username: user.username,
       password: user.password
     }).then(fnSuccess, fnFailure);
     console.log("[DONE] GET /user -> " + user.username);
   }
  }
}])
