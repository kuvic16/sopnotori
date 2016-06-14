app.registerCtrl('EditStudentFeeController', function($scope, $routeParams, $location, StudentFeeResource, InstituteResource,GradeResource,FeeResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.feeLists=[];
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.studentFee = new StudentFeeResource(self.original);
            
            if (angular.isUndefined($scope.studentFee.instituteId) || $scope.studentFee.instituteId === null) {
        		$scope.studentFee.instituteId = "";
        	}
            
            if (angular.isUndefined($scope.studentFee.gradeId) || $scope.studentFee.gradeId === null) {
            	$scope.studentFee.gradeId = "";
        	}
        };
        var errorCallback = function() {
            $location.path("/StudentFee");
        };
        StudentFeeResource.get({StudentFeeId:$routeParams.StudentFeeId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.studentFee);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/StudentFee");
        	//$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        //console.log($scope.studentFee.mandatory);
        //$scope.studentFee.isMandatory = JSON.parse("true");;
        //console.log($scope.studentFee);
        $scope.studentFee.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/StudentFee");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/StudentFee");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.studentFee.$remove(successCallback, errorCallback);
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
    
    $scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
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
		
	$scope.getFeeList = function(){
		var successCallback = function(data, responseHeaders){
			$scope.feeLists = data.model;
		};
		var errorCallback = function(){
			console.log('error');
		};
		FeeResource.queryAll({all: true}, successCallback, errorCallback);
	}
    
    $scope.get();
    $scope.getFeeList();
    $scope.getInstituteList();
    $scope.getGradeList();
});