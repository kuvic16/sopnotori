app.registerCtrl('NewGradeController', function ($scope, $location, locationParser, GradeResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.grade = $scope.grade || {};
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Grade");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        GradeResource.save($scope.grade, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Grade");
    };
});