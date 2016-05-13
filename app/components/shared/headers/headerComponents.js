var app = angular.module('web-gui-app.shared.headers', [])

.component('visitantHeader', {
  templateUrl: '/app/components/shared/headers/vistantHeader.html',
  controller: 'visitantHeaderController'
})

.component('userHeader', {
  templateUrl: '/app/components/shared/headers/userHeader.html',
  controller: 'userHeaderController'
})
