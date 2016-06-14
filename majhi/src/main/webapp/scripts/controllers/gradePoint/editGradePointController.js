app.registerCtrl('EditGradePointController', function($scope, $routeParams, 
		$location, GradePointResource, EducationTypeResource,
		rexValidation) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.educationTypeList = [];
    $scope.validation = rexValidation;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.gradePoint = new GradePointResource(self.original);
            console.log($scope.gradePoint);
        };
        var errorCallback = function() {
            $location.path("/GradePoint");
        };
        GradePointResource.get({GradePointId:$routeParams.GradePointId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.grade_point);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/GradePoint");
            //$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.gradePoint.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/GradePoint");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/GradePoint");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.gradePoint.$remove(successCallback, errorCallback);
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
    
    $scope.getEducationTypeList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.educationTypeList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        EducationTypeResource.list(successCallback, errorCallback);
    };
    
    $scope.get();
    $scope.getEducationTypeList();
});