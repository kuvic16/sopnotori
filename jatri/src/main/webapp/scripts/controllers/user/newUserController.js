app.registerCtrl('NewUserController', function ($scope, $location, locationParser, UserResource, 
		UdvResource, RoleResource, LocationResource, OrganizationResource,InstituteResource,$rootScope,rexValidation) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.user = $scope.user || {};
    $scope.udvList = [];
    $scope.roleList = [];
    $scope.organizationList = [];
    $scope.instituteList = [];
    $scope.validation = rexValidation;
    
    //$scope.focusMe="false";
    
    $scope.usernameAndPasswordFieldEnable = false;
     
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		if(data.success==2){
    			$.growl.error({ message: ""+data.message });
    			//$scope.focusMe="true";
    		}
    		else{
	    		$.growl.notice({ message: "New User Created Successfully!" });
	            $location.path("/User");
	            $scope.displayError = false;
    		}
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        $scope.user.locationId = LocationResource.getSelectedLocationId();
        $scope.user.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        
        $scope.user.username = $scope.user.username.toLowerCase().trim();
        
        UserResource.save($scope.user, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/User");
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
	
	$scope.clearFields = function() {
		$scope.user.username=null;
		$scope.user.email=null;
		$scope.user.password=null;
		$scope.user.firstName=null;
		$scope.user.middleName=null;
		$scope.user.lastName=null;
		$scope.user.fatherName=null;
		$scope.user.motherName=null;
		$scope.user.husbandName=null;
		$scope.user.mobileNumber=null;
		$scope.user.dateOfBirth=null;
		//$scope.user.gender=null;
		$scope.user.status=null;
		//$scope.user.designationUdvId=null;
		//$scope.user.roleId=null;
		//$scope.user.upLevelId=null;
		$scope.user.imagePath=null;
		$scope.user.locationId=null;
		//$scope.user.userCategoryUdvId=null;
		//$scope.user.userHierarchyId=null;
		//$scope.user.locationTypeUdvId=null;
		$scope.user.latitude=null;
		$scope.user.longitude=null;
		/*$scope.user.organizationId=null;
		$scope.user.instituteId=null;
		$scope.user.locationHierarchy=null;
		$scope.user.staffGrageUdvId=null;
		$scope.user.dropOut=null;
		$scope.user.dropOutReason=null;
		$scope.user.bracGraduate=null;
		$scope.user.teachingExpBrac=null;
		$scope.user.replacement=null;
		$scope.user.maritalStatus=null;*/
		$scope.usernameAndPasswordFieldEnable = false;
		
	}
	    
	$scope.loadInitInstitute();
	$scope.usernameEnable = true;
	setTimeout($scope.clearFields, 50);
	
	//$scope.clearFields();
    $scope.getUdvList('Location type, User category, Designation, Staff grade');
    $scope.getRoleList();
    $scope.getOrganizationList();
    //$scope.getInstituteList();
});