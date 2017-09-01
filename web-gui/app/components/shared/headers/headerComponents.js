var app = angular.module('web-gui-app.shared.headers', [])

.component('visitantHeader', {
  templateUrl: '/app/components/shared/headers/vistantHeader.html',
  bindings: { $router: '<' },
  controller: 'visitantHeaderController'
})

.component('userHeader', {
  templateUrl: '/app/components/shared/headers/userHeader.html',
  controller: 'userHeaderController'
})
