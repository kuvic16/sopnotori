app.registerCtrl('NewRightController', function ($scope, $location, locationParser, RightResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.right = $scope.right || {};
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = locationParser(responseHeaders);
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Rights");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        RightResource.save($scope.right, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Rights");
    };
});