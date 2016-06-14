app.registerCtrl('NewGradingSystemController', function ($scope, $location, locationParser, GradingSystemResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.grading_system = $scope.grading_system || {};
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/GradingSystem");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        GradingSystemResource.save($scope.grading_system, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/GradingSystem");
    };
});