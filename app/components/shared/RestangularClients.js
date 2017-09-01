var app = angular.module('web-gui-app.shared.restangular', [])

.factory('userServiceClient', ["Restangular", function(Restangular) {
  return Restangular.withConfig(function(RestangularConfigurer) {
    RestangularConfigurer.setBaseUrl("http://localhost:50001");
  })
}])

.factory('postServiceClient', ["Restangular", function(Restangular) {
  return Restangular.withConfig(function(RestangularConfigurer) {
    RestangularConfigurer.setBaseUrl("http://localhost:50002");
  })
}]);
