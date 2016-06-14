app.registerCtrl('NewTeacherController', function (
													$scope, 
													$location, 
													locationParser, 
													UserResource, 
													UdvResource, 
													RoleResource, 
													OrganizationResource,
													InstituteResource,
													LocationResource, 
													$rootScope,
													rexValidation
												  ) 
{
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
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		if(data.success==2){
    			$.growl.error({ message: ""+data.message });
    			//$scope.focusMe="true";
    		}
    		else{
	    		$.growl.notice({ message: "New User Created Successfully!" });
	    		$location.path("/Teacher");
	            $scope.displayError = false;
    		}
            
            
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        $scope.user.locationId = LocationResource.getSelectedLocationId();
        $scope.user.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        $scope.user.username = $scope.user.username.toLowerCase().trim();
        $scope.user.onlyTeacher=true;
        UserResource.save($scope.user, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Teacher");
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
    
    // Load institute by location hierarchy ===========================================================================
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
    //================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.user.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.user.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.user.locationHierarchy);
    	}
    });
  //===============================================================================================
    
    $scope.getOrganizationList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.organizationList = data.model;
    		
    		// support for watch work perfectly
			//$scope.pageLoaded = true;
			//=================================
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        OrganizationResource.queryAll({all: true},successCallback, errorCallback);
    };
    
    /*$scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
	};*/
    
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
    $scope.getUdvList('Location type, Designation, Staff grade');
    $scope.getRoleList();
    $scope.getOrganizationList();
    //$scope.getInstituteList();
});