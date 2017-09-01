var app = angular.module('web-gui-app.user')

.factory('userService', ["userServiceClient", function(userServiceClient) {

  var baseUsers = userServiceClient.all('user');
  var loggedUser;

  return {
   storeUserInSession: user => { loggedUser = user; },
   loggedUser: () => { return loggedUser; },
   save: (user, fnSuccess, fnFailure) => {
     baseUsers.post(user).then(fnSuccess, fnFailure);
   },
   authenticate: (user, fnSuccess, fnFailure) => {
     baseUsers.get("", {
       username: user.username,
       password: user.password
     }).then(fnSuccess, fnFailure);
   },
   findByUsername: (username, fnSuccess, fnFailure) => {
     return baseUsers.get(username).then(fnSuccess, fnFailure);
   }
  }
}])
