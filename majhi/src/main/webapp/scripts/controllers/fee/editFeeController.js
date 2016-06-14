app.registerCtrl('EditFeeController', function($scope, $routeParams, $location, FeeResource, UdvResource, InstituteResource, GradeResource, rexValidation, LocationResource, $rootScope ) 
{
    var self = this;
    $scope.disabled = false;
    $scope.fee = $scope.fee || {};
    $scope.$location = $location;
    $scope.udvList = [];
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.validation = rexValidation; ///^\d*$/;
    $scope.feeTypeName ="";
    
    $scope.instituteDisable=true;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.fee = new FeeResource(self.original);
            
            if (angular.isUndefined($scope.fee.instituteId) || $scope.fee.instituteId === null) {
        		$scope.fee.instituteId = "";
        	}
            
            if (angular.isUndefined($scope.fee.gradeId) || $scope.fee.gradeId === null) {
        		$scope.fee.gradeId = "";
        	}
            
            // Set location
            if($scope.fee.locationHierarchy !== ''){
            	LocationResource.setLocation($scope.fee.locationId);
            }          
            
            if($scope.fee.locationHierarchy !== ''){
            	$scope.getInstituteList($scope.fee.locationHierarchy);
            } 
            
            for(udv in $scope.udvList) {
                //console.log($scope.udvList[udv].value);
                if($scope.fee.feeTypeUdvId==$scope.udvList[udv].id)
                		$scope.feeTypeName =$scope.udvList[udv].value;
               }
            
        };
        
        var errorCallback = function() {
            $location.path("/Fee");
        };        
        FeeResource.get({FeeId:$routeParams.FeeId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.fee);
    };

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
        	if(data.success === "1"){
	        	$.growl.notice({ message: "Updated successfully!" });
	        	$location.path("/Fee");
	            $scope.displayError = false;
        	}else{
    			$.growl.error({ message: data.message });
    			$scope.get();
    		}
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        $scope.fee.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Fee");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Fee");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.fee.$remove(successCallback, errorCallback);
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
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
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
		InstituteResource.hierarchy({id : locationHierarchy}, successCallback, errorCallback);
    };
   //=====================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange'====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.fee.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.fee.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.fee.locationHierarchy);
    	}
    });
    //============================================================================================
    
   	
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
   
	$scope.onInstituteChange = function(instituteId) {
    	$scope.getInstituteDetails(instituteId);
    	$scope.getGradeList(instituteId);
    }
    
	$scope.getGradeList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.queryAll({all : true}, successCallback, errorCallback);
	};
	
	$scope.getInstituteDetails = function(instituteId) {
		var successCallback = function(data) {
			$scope.fee.gradeId = data.model.currentGradeId;
		};

		var errorCallback = function() {
		};
		InstituteResource.get({InstituteId : instituteId}, successCallback, errorCallback);
	};
	
	$scope.getFeeTypeUdvDetails = function(feeTypeUdvId) {
		var successCallback = function(data){
    		$scope.fee.code = data.model.altValue;
        };
        var errorCallback = function() {
            
        };
        UdvResource.get({UdvId:feeTypeUdvId}, successCallback, errorCallback);
	};
	
	$scope.loadInitInstitute();
    $scope.udvList('Fee type,Fee category'); 
    //$scope.getInstituteList();
    $scope.getGradeList();
    $scope.get();
});