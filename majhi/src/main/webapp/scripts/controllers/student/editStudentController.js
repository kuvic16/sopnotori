app.registerCtrl('EditStudentController', function
													(
														$scope, 
														$routeParams, 
														$location, 
														StudentResource, 
														UdvResource, 
														InstituteResource,
														GradeResource,
														rexValidation,
														LocationResource,
														$rootScope
													) 
{
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.student = $scope.student || {};
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.validation = rexValidation;
    
    $scope.instituteDisable=true;
       
    $scope.get = function() 
    {
        var successCallback = function(data)
        {
            self.original = data.model;
            $scope.student = new StudentResource(self.original);
                        
            //$scope.student.familyMembersInvolveWithBrac =  Boolean($scope.student.familyMembersInvolveWithBrac);
            //$scope.student.transferedSchool = Boolean($scope.student.transferedSchool);
            
            
            
            // $scope.student.involvedWithChhatrabandhu = Boolean($scope.student.involvedWithChhatrabandhu);
            // $scope.student.bracGraduate = Boolean($scope.student.bracGraduate);
            // $scope.student.dropout = Boolean($scope.student.dropout);
            $scope.student.waiver = parseInt($scope.student.waiver);
            
           
            
            		
            
            // Load grade by institute
            $scope.getGradeList(data.model.instituteId);
            
            if (angular.isUndefined($scope.student.instituteId) || $scope.student.instituteId === null) {
        		$scope.student.instituteId = "";
        	}
            
            if (angular.isUndefined($scope.student.gradeId) || $scope.student.gradeId === null) {
            	$scope.student.gradeId = "";
        	}
            
            // Set location
            if($scope.student.locationId !== ''){
				LocationResource.setLocation($scope.student.locationId);
            }
            
            if($scope.student.locationHierarchy !== ''){
            	$scope.getInstituteList($scope.student.locationHierarchy);
            }
        };
        var errorCallback = function() 
        {
            $location.path("/Student");
        };
        StudentResource.get({StudentId:$routeParams.StudentId}, successCallback, errorCallback);
      
    };

    $scope.isClean = function() 
    {
        return angular.equals(self.original, $scope.student);
    };

    $scope.save = function() 
    {
        var successCallback = function()
        {
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Student");
            //$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() 
        {
            $scope.displayError=true;
        };
        
        $scope.student.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() 
    {
    	$location.path("/Student");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Student");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.student.$remove(successCallback, errorCallback);
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
    
    $scope.udvList = function(cateogryNames) 
    {
    	var successCallback = function(data,responseHeaders) 
    	{
    		$scope.udvList = data.model;
        };
        var errorCallback = function() 
        {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    // Load institute by location hierarchy =============================================================================
    $scope.getInstituteList = function(locationHierarchy) {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
			$scope.instituteDisable = false;
		};
		var errorCallback = function() {
			console.log('error');
		};		
		//InstituteResource.queryAll({locationHierarchy : locationHierarchy}, successCallback, errorCallback);
		InstituteResource.hierarchy({id : locationHierarchy}, successCallback, errorCallback);
    };
   //=====================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange'====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.student.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.student.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.student.locationHierarchy);
    	}
    });
    //============================================================================================
        
    $scope.getGradeList = function(instituteId) {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.listByInstituteId({InstituteId: instituteId}, successCallback, errorCallback);
    };
    
    $scope.loadInitInstitute = function(){
		var userHierarchy = $rootScope.loogedUser.locationHierarchy;
		if($rootScope.loogedUser.username !== 'admin'){
	    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
	    	var hierarchy = userHierarchy.split(">");
			if(hierarchy.length == 5){
				$scope.getInstituteList(userHierarchy);
			}
	    }
	};
    
	$scope.loadInitInstitute();
    $scope.udvList('Student type, Csn type, Ethnicity community type, Dialect spoken, Religion'); 
    //$scope.getInstituteList();
    //$scope.getGradeList();
    $scope.get();
});