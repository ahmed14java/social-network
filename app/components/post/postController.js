var app = angular.module('web-gui-app.post', [])

.controller('postController', ["$scope", "timelineService", "userService", "postService",
  function ($scope, timelineService, userService, postService) {

  $scope.save = (post) => {
    var postToBeSaved = {};

    angular.copy(post, postToBeSaved);
    postToBeSaved.username = userService.loggedUser().username;

    var fnSuccess = () => { timelineService.push(postToBeSaved); }

    var fnFailure = (response) => {
      console.log("[FAILED] POST " + response.config.url +
                  " [CODE] " + response.status, response.status);
    }

    postService.save(postToBeSaved, fnSuccess, fnFailure);

    angular.copy({}, post);
  }

  $scope.trixAttachmentAdd = function(e) {
      var attachment;
      attachment = e.attachment;
      if (attachment.file) {
          return uploadAttachment(attachment);
      }
  }

  $scope.imageUpload = function(files) {
    console.log('image upload:', files);
    console.log('image upload\'s editable:', $scope.editable);
  }

  host = "http://localhost:50003/image";

  uploadAttachment = function(attachment) {
      var file, form, key, xhr;
      file = attachment.file;
      form = new FormData;
      form.append("Content-Type", file.type);
      form.append("image", file);
      xhr = new XMLHttpRequest;
      xhr.open("POST", host, true);
      xhr.upload.onprogress = function(event) {
          var progress;
          progress = event.loaded / event.total * 100;
          return attachment.setUploadProgress(progress);
      };
      xhr.onload = function(e) {
          var href, url;
          console.log("BLAH BLAH ");
          console.log(xhr.response);
          if (xhr.status === 200) {
              url = href = host + "/" + xhr.response;
              console.log(url);
              console.log(href);
              return attachment.setAttributes({
                  url: url,
                  href: href
              });
          }
      };
      return xhr.send(form);
  };

}]);
