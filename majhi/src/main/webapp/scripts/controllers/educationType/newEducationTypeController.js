app.registerCtrl('NewEducationTypeController', function ($scope, $location, locationParser, EducationTypeResource, GradePointResource, GradeResource  ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.educationType = $scope.educationType || {};
    $scope.gradePointList = [];
    $scope.gradeList=[];
    $scope.selectedGrade = { 
    	grade: []
    };
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/EducationType");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        $scope.educationType.gradeModelList = $scope.selectedGrade.grade;
        EducationTypeResource.save($scope.educationType, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/EducationType");
    };
    
    $scope.getGradeList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.queryAll({all : true}, successCallback, errorCallback);
	};
	$scope.getGradeList();
});