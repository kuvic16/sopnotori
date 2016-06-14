app.registerCtrl('NewGradePointController', function ($scope, $location, 
		locationParser, GradePointResource, EducationTypeResource
		) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.gradePoint = $scope.gradePoint || {};
    $scope.educationTypeList = [];
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/GradePoint");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        GradePointResource.save($scope.gradePoint, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/GradePoint");
    };
    
    $scope.getEducationTypeList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.educationTypeList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        EducationTypeResource.list(successCallback, errorCallback);
    };
    
    $scope.getEducationTypeList();
});