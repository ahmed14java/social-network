var app = angular.module('web-gui-app.timeline', [])

.component('timelineComponent', {
  template: '<user-header></user-header><div class="container"><ng-outlet></ng-outlet></div>',
  $routeConfig: [
    { path: '/', name: 'TimelineIndex', component: 'timelineIndexComponent' },
  ]
})

.component('timelineIndexComponent', {
  templateUrl: '/app/components/timeline/index.html'
});
