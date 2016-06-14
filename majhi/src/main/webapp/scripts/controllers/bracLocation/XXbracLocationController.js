app.registerCtrl('XBracLocationController', function($scope, LocationResource, $rootScope) {
	$scope.brac = {};
	$scope.brac.divisionList = [];
    $scope.brac.districtList = [];
    $scope.brac.upazilaList = [];
    $scope.brac.unionList = [];
    
    $scope.brac.selectedDivisionId = 'NULL';    
    $scope.brac.selectedDistrictId = '';
    $scope.brac.selectedUpazilaId = '';
    $scope.brac.selectedUnionId = '';
    
    $scope.brac.visibilityScope='';
    
    $rootScope.watchForLocationChange=null;
    
    // Load division list
    $scope.brac.getDivisionList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.brac.divisionList = data.listOfLocation;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        //LocationResource.getAllDivision(successCallback, errorCallback);
        LocationResource.getAllRegion(successCallback, errorCallback);
        
        //$rootScope.watchForLocationChange="Division";
    	//console.log("-------------------------Division----------" + $scope.brac.selectedDivisionId);
    };
    
    // Load district list
    $scope.brac.getDistrictList = function(divisionId) {
    	if(divisionId !== '' && divisionId !== 'NULL'){
	    	var successCallback = function(data,responseHeaders) {
	    		$scope.brac.districtList = data.listOfLocation;
	    		//$scope.brac.selectedDistrictId = '';
	    		//$scope.brac.selectedUpazilaId = '';
	    		$scope.brac.upazilaList = [];
	        };
	        var errorCallback = function() {
	        	console.log('error');
	        };
	      
	        //LocationResource.getAllDistrictByDivisionId({id:divisionId}, successCallback, errorCallback);
	        LocationResource.getAllArea({parentId:divisionId}, successCallback, errorCallback);
    	}else{
    		$scope.brac.selectedDistrictId = '';
    	    $scope.brac.selectedUpazilaId = '';
    	    $scope.brac.selectedUnionId = '';
    	    $scope.brac.districtList = [];
    	    $scope.brac.upazilaList = [];
    	    $scope.brac.unionList = [];
    	    
    	}
    		
    	$rootScope.watchForLocationChange=$scope.brac.selectedDivisionId;
    	console.log("--------------------District------------------------" + $scope.brac.selectedDistrictId);
    };
    
    // Load upazila list
    $scope.brac.getUpazilaList = function(districtId) {
    	if(districtId==''){
    		$scope.brac.upazilaList = [];
    		$scope.brac.selectedUpazilaId = '';
    	}
    	if(districtId !== ''){
	    	var successCallback = function(data,responseHeaders) {
	    		$scope.brac.upazilaList = data.listOfLocation;
	        };
	        var errorCallback = function() {
	        	console.log('error');
	        }; 
	        //LocationResource.getAllUpazilaByDistrictId({id:districtId}, successCallback, errorCallback);
	        LocationResource.getAllBranch({parentId:districtId}, successCallback, errorCallback);
    	}
    	//$rootScope.watchForLocationChange = true; 
    	//$rootScope.watchForLocationChange="Area";
    	$rootScope.watchForLocationChange = !$rootScope.watchForLocationChange;
    	console.log("--------------------Area---------------" + $scope.brac.selectedUpazilaId);
    };
    
    $scope.brac.getBranch = function() {
    	//$rootScope.watchForLocationChange="Branch";
    	$rootScope.watchForLocationChange = !$rootScope.watchForLocationChange;
    	console.log("--------------------Branch-------------" + $rootScope.watchForLocationChange);
	};
    
    LocationResource.setLocation = function(locationId) {
    	var successCallback = function(data,responseHeaders) {
    		if(data.responseCode == '200' && data.hierarchyId !== ''){
    			var hierarchy = data.hierarchyId.split(">");
    			$scope.brac.visibilityScope = hierarchy;
    			if(hierarchy.length == 5){
    				$scope.brac.selectedDivisionId = hierarchy[1];
    				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
    				
    				$scope.brac.selectedDistrictId = hierarchy[2];
    				$scope.brac.getUpazilaList($scope.brac.selectedDistrictId);
    				
    				$scope.brac.selectedUpazilaId = hierarchy[3];
    				
    				
    				
    			}else if(hierarchy.length == 4){
    				$scope.brac.selectedDivisionId = hierarchy[1];
    				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
    				
    				$scope.brac.selectedDistrictId = hierarchy[2];
    				$scope.brac.getUpazilaList($scope.brac.selectedDistrictId);
    			}else if(hierarchy.length == 3){
    				$scope.brac.selectedDivisionId = hierarchy[1];
    				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
    			}
    		}
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        LocationResource.getLocationById({id:locationId}, successCallback, errorCallback);
    };
    

    // get selected division id
    LocationResource.getSelectedDivisionId = function(){
    	return $scope.brac.selectedDivisionId;
    };
    
    // get selected division hierarchy id
    LocationResource.getSelectedDivisionHierarchyId = function(divisionId){
    	for(i in $scope.brac.divisionList){
    		if($scope.brac.divisionList[i].id === divisionId){
    			return $scope.brac.divisionList[i].hierarchyId;
    		}
    	}
    	return "";
    };
    
    // get selected district id
    LocationResource.getSelectedDistrictId = function(){
    	return $scope.brac.selectedDistrictId;
    };
    
    LocationResource.getSelectedDistrictHierarchyId = function(districtId){
    	for(i in $scope.brac.districtList){
    		if($scope.brac.districtList[i].id === districtId){
    			return $scope.brac.districtList[i].hierarchyId;
    		}
    	}
    	return "";
    };
    
    // get selected upazila id
    LocationResource.getSelectedUpazilaId = function(){
    	return $scope.brac.selectedUpazilaId;
    };
    
    LocationResource.getSelectedUpazilaHierarchyId = function(upazilaId){
    	for(i in $scope.brac.upazilaList){
    		if($scope.brac.upazilaList[i].id === upazilaId){
    			return $scope.brac.upazilaList[i].hierarchyId;
    		}
    	}    	
    	return "";
    };
    
    // get selected union id
    LocationResource.getSelectedUnionId = function(){
    	return $scope.brac.selectedUnionId;
    };
    
    // get selected location id
    LocationResource.getSelectedLocationId = function(){
    	var selectedLocationId = "";
        if($scope.brac.selectedUpazilaId !== ''){
        	selectedLocationId = $scope.brac.selectedUpazilaId; 
        }else if($scope.brac.selectedDistrictId !== ''){
        	selectedLocationId = $scope.brac.selectedDistrictId; 
        }else if($scope.brac.selectedDivisionId !== '' && $scope.brac.selectedDivisionId !== 'NULL'){
        	selectedLocationId = $scope.brac.selectedDivisionId; 
        }
    	return selectedLocationId;
    };
    
    // get selected location hierarchy id
    LocationResource.getSelectedLocationHierarchyId = function(){
    	var selectedLocationHierarchyId = "";
        if($scope.brac.selectedUpazilaId !== ''){
        	selectedLocationHierarchyId = LocationResource.getSelectedUpazilaHierarchyId($scope.brac.selectedUpazilaId);
        }else if($scope.brac.selectedDistrictId !== ''){
        	selectedLocationHierarchyId = LocationResource.getSelectedDistrictHierarchyId($scope.brac.selectedDistrictId);
        }else if($scope.brac.selectedDivisionId !== '' && $scope.brac.selectedDivisionId !== 'NULL'){
        	selectedLocationHierarchyId = LocationResource.getSelectedDivisionHierarchyId($scope.brac.selectedDivisionId);
        }
    	return selectedLocationHierarchyId;
    };
    
    $scope.brac.checkAbility = function(region) {
    	if($rootScope.loogedUser.username !== 'admin'){
			if($scope.brac.visibilityScope.length==5){
				return true;
			}
			if($scope.brac.visibilityScope.length==4){
				if(region === 'division' || region === 'district' ){
					return true;
				}
			}
			
			if($scope.brac.visibilityScope.length==3){
				if(region === 'division' ){
					return true;
				}
			}
    	}
		return false;
		
	}
    
    LocationResource.setLoggedUserLocation = function(locationHierarchy) {
    	if(locationHierarchy !== ''){
			var hierarchy = locationHierarchy.split(">");
			if(hierarchy.length == 5){
				$scope.brac.selectedDivisionId = hierarchy[1];
				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
				
				$scope.brac.selectedDistrictId = hierarchy[2];
				$scope.brac.getUpazilaList($scope.brac.selectedDistrictId);
				
				$scope.brac.selectedUpazilaId = hierarchy[3];
			}else if(hierarchy.length == 4){
				$scope.brac.selectedDivisionId = hierarchy[1];
				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
				
				$scope.brac.selectedDistrictId = hierarchy[2];
				$scope.brac.getUpazilaList($scope.brac.selectedDistrictId);
			}else if(hierarchy.length == 3){
				$scope.brac.selectedDivisionId = hierarchy[1];
				$scope.brac.getDistrictList($scope.brac.selectedDivisionId);
			}
		}
    };
    
    $scope.brac.getDivisionList();
    if($rootScope.loogedUser.username !== 'admin'){
    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
		$scope.brac.visibilityScope = userHierarchy.split(">");
		LocationResource.setLoggedUserLocation(userHierarchy);
    }
});