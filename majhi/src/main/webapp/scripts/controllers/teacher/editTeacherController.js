app.registerCtrl('EditTeacherController', function($scope, $routeParams, $location, UserResource, UdvResource, RoleResource, OrganizationResource, InstituteResource, 
													LocationResource, $rootScope, rexValidation ){
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.user = $scope.user || {};
    $scope.udvList = [];
    $scope.roleList = [];
    $scope.organizationList = [];
    $scope.instituteList = [];
    $scope.validation = rexValidation;
    
    $scope.instituteDisable=true;
    // For watch event work correctly =================
    $scope.pageLoaded = false;
    //=================================================
    
    $scope.get = function() {
    	console.log('success call');
        var successCallback = function(data,responseHeaders){
        	self.original = data.model;
            $scope.user = new UserResource(self.original);
            //$scope.user = data.model;
            /*
            if($scope.user.locationId !== ''){
            	LocationResource.setLocation($scope.user.locationId);
            }
            */
            if(angular.isUndefined($scope.user.organizationId) || $scope.user.organizationId === null){
            	$scope.user.organizationId = "";
            }
            
            if (angular.isUndefined($scope.user.instituteId) || $scope.user.instituteId === null) {
        		$scope.user.instituteId = "";
        	}
            
            // Set location
            if($scope.user.locationId !== ''){
				LocationResource.setLocation($scope.user.locationId);
            }
                        
            if($scope.user.locationHierarchy !== ''){
            	$scope.getInstituteList($scope.user.locationHierarchy);
            }
            $('#dateOfBirth').datepicker('setDate',$scope.user.dateOfBirth);
            
        };
        var errorCallback = function() {
        	console.log("Error call back");
            $location.path("/Teacher");
        };
        UserResource.get({UserId:$routeParams.TeacherId}, successCallback, errorCallback);
        
        // For watch event work correctly =================
        $scope.pageLoaded =  true;
        //=================================================
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.user);
    };

    var successCallback = function(data,responseHeaders){
    	
    	if(data.success==2){
			$.growl.error({ message: ""+data.message });
			$scope.user = data.model;
		}else{
			
			$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/User");
            $scope.displayError = false;
		}
    };
    
    $scope.save = function() {
    	 var successCallback = function(data,responseHeaders){
    	    	
    	    	if(data.success==2){
    				$.growl.error({ message: ""+data.message });
    				$scope.user = data.model;
    			}else{
    				
    				$.growl.notice({ message: "Updated successfully!" });
    	        	$location.path("/Teacher");
    	            $scope.displayError = false;
    			}
    	    };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        $scope.user.locationId = LocationResource.getSelectedLocationId();
        $scope.user.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        $scope.user.username = $scope.user.username.toLowerCase().trim();
        $scope.user.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Teacher");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Teacher");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.user.$remove(successCallback, errorCallback);
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
    
    $scope.getUdvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.getRoleList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.roleList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        RoleResource.queryAll({all: true},successCallback, errorCallback);
    };
    
    $scope.getOrganizationList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.organizationList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        OrganizationResource.queryAll({all: true},successCallback, errorCallback);
    };
    
   /* $scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
	};*/
    
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
    	$scope.user.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.user.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.user.locationHierarchy);
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
    
    $scope.getUdvList('Location type, Designation, Staff grade');
    $scope.get();
    $scope.loadInitInstitute();
    $scope.getRoleList();
    $scope.getOrganizationList();
    //$scope.getInstituteList();
});