var app = angular.module('web-gui-app.welcome', [])

.component('welcomeComponent', {
  template: '<visitant-header></visitant-header><div class="container"><ng-outlet></ng-outlet></div>',
  $routeConfig: [
    { path: '/', name: 'Index', component: 'indexComponent', useAsDefault: true },
    { path: '/newUser', name: 'NewUser', component: 'newUser' }
  ]
})

.component('indexComponent', {
  templateUrl: '/app/components/welcome/index.html'
});
