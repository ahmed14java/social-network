var app = angular.module('web-gui-app.user', [])

.component('user', {
  template: '<ng-outlet></ng-outlet>',
  $routeConfig: [
    { path: '/new', name: 'NewUser', component: 'newUser', useAsDefault: true },
    { path: '/list', name: 'ListUser', component: 'listUser' }
  ]
})

.component('newUser', {
  templateUrl: '/app/components/user/new.html'
})

.component('listUser', {
  templateUrl: '/app/components/user/list.html'
});
