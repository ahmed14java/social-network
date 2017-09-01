var app = angular.module('web-gui-app.post', [])

.controller('postController', ["$scope", "timelineService", "user", "postService",
  function ($scope, timelineService, user, postService) {

   $scope.user = user;

   $scope.froalaOptions = {
        toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough',
                         'insertLink', 'insertImage', 'insertVideo'],
        imageUploadParam: 'image',
        imageUploadURL: 'http://localhost:50003/image',
        imageUploadMethod: 'POST',
        imageMaxSize: 1024 * 1024, // 1 MB
        imageAllowedTypes: ['jpeg', 'jpg', 'png'],
        events: {
          'froalaEditor.image.error': function(e, editor, error, response) {
            console.log(e);
            console.log(editor);
            console.log(error);
            console.log(response);
          }
        }
   }

  $scope.save = (post) => {
    var postToBeSaved = {};

    angular.copy(post, postToBeSaved);
    postToBeSaved.username = user.username;

    var fnSuccess = () => { timelineService.push(postToBeSaved); }

    var fnFailure = (response) => {
      console.log("[FAILED] POST " + response.config.url +
                  " [CODE] " + response.status, response.status);
    }

    postService.save(postToBeSaved, fnSuccess, fnFailure);

    angular.copy({}, post);
  }

}]);
