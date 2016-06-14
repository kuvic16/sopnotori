app.registerCtrl('EditEducationTypeController', function($scope, $routeParams, $location, EducationTypeResource, GradePointResource, GradeResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.gradePointList = [];
    $scope.gradeList=[];
    $scope.selectedGrade = { 
    	grade: []
    };
    
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.educationType = new EducationTypeResource(self.original);
            $scope.getGradePoint($scope.educationType.id);
            $scope.getGradeByEducationTypeId($scope.educationType.id);
        };
        var errorCallback = function() {
            $location.path("/EducationType");
        };
        EducationTypeResource.get({EducationTypeId:$routeParams.EducationTypeId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.educationType);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/EducationType");
            //$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.educationType.gradeModelList = $scope.selectedGrade.grade;
        $scope.educationType.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/EducationType");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/EducationType");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.educationType.$remove(successCallback, errorCallback);
            },
            cancel: function(button) {
            	return false;
            },
            confirmButton: "Yes",
            cancelButton: "Nope",
            confirmButtonClass: "btn-danger",
            cancelButtonClass: "btn-info"
        });
    };
    
    $scope.getGradePoint = function(educationTypeId) {
        var successCallback = function(data){
            $scope.gradePointList = data.model;
        };
        var errorCallback = function() {
            console.log('error');
        };
        GradePointResource.listByEducationType({EducationTypeId: educationTypeId}, successCallback, errorCallback);
    };
    
    $scope.getGradeByEducationTypeId = function(educationTypeId) {
        var successCallback = function(data){
            $scope.selectedGrade.grade = angular.copy(data.model);
        };
        var errorCallback = function() {
            console.log('error');
        };
        GradeResource.listByEducationType({EducationTypeId: educationTypeId}, successCallback, errorCallback);
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
    
    $scope.get();
    $scope.getGradeList();
});