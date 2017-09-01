var app = angular.module('web-gui-app.shared.directives', [])

.directive('serviceValidated', function () {
    return {
      restrict: 'A',
      require: 'ngModel',
      link: function (scope, element, attrs, ctrl) {
        scope.$watch(attrs.ngModel, function (v) {
          for(var errorKey in ctrl.$error)
            if (errorKey.indexOf("user.") == 0)
              ctrl.$setValidity(errorKey, true);
        });
      }
    };
  });
