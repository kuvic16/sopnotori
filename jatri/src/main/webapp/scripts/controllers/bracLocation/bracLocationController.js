app.registerCtrl('BracLocationController', function($scope, LocationResource, $rootScope) {
	$scope.brac = {};
	$scope.brac.regionList = [];
    $scope.brac.areaList = [];
    $scope.brac.branchList = [];
    
    $scope.brac.selectedRegionId = '';    
    $scope.brac.selectedAreaId = '';
    $scope.brac.selectedBranchId = '';
    
    $scope.brac.visibilityScope='';
    
    $rootScope.watchForLocationChange=false;
    
    
    /**
     * Load region list
     */
    $scope.brac.getRegionList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.brac.regionList = data.listOfLocation;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        LocationResource.getAllRegion(successCallback, errorCallback);
    };
    
    
    /**
     * Load area list
     */
    $scope.brac.getAreaList = function(regionId) {
    	if(regionId !== '' && regionId !== 'NULL'){
	    	var successCallback = function(data,responseHeaders) {
	    		$scope.brac.areaList = data.listOfLocation;
	        };
	        var errorCallback = function() {
	        	console.log('error');
	        };
	        LocationResource.getAllArea({parentId:regionId}, successCallback, errorCallback);
    	}
    };
    
    
    
    /**
     * Load branch list
     */
    $scope.brac.getBranchList = function(areaId) {
    	if(areaId !== ''){
	    	var successCallback = function(data,responseHeaders) {
	    		$scope.brac.branchList = data.listOfLocation;
	        };
	        var errorCallback = function() {
	        	console.log('error');
	        }; 
	        LocationResource.getAllBranch({parentId:areaId}, successCallback, errorCallback);
    	}
    };
    
    
    
    /**
     * Region dropdown change listener
     */
    $scope.brac.onRegionChange = function(selectedRegionId) {
    	$scope.brac.areaList = [];
    	$scope.brac.branchList = [];
    	$scope.brac.selectedAreaId = '';
        $scope.brac.selectedBranchId = '';
    	$scope.brac.getAreaList(selectedRegionId);
    	$rootScope.watchForLocationChange = !$rootScope.watchForLocationChange;
    };
    
    
    /**
     * Area dropdown change listener
     */
    $scope.brac.onAreaChange = function(selectedAreaId) {
    	$scope.brac.branchList = [];
    	$scope.brac.selectedBranchId = '';
    	$scope.brac.getBranchList(selectedAreaId);
    	$rootScope.watchForLocationChange = !$rootScope.watchForLocationChange;
    };
    
    
    /**
     * Branch dropdown change listener
     */    
    $scope.brac.onBranchChange = function(selectedBranchId) {
    	$rootScope.watchForLocationChange = !$rootScope.watchForLocationChange;
    };
    
    
    
    LocationResource.unBindLocation = function(){
    	$scope.brac.areaList   = [];
    	$scope.brac.branchList = [];
    	$scope.brac.selectedRegionId = '';
    	$scope.brac.selectedAreaId = '';
        $scope.brac.selectedBranchId = '';
    }
    
    /**
     * Populate all dropdown according to locationId
     */    
    LocationResource.setLocation = function(locationId) {
    	var successCallback = function(data,responseHeaders) {
    		if(data.responseCode == '200' && data.hierarchyId !== ''){
    			var hierarchy = data.hierarchyId.split(">");
    			$scope.brac.visibilityScope = hierarchy;
    			if(hierarchy.length == 5){
    				$scope.brac.selectedRegionId = hierarchy[1];
    				$scope.brac.getAreaList($scope.brac.selectedRegionId);
    				
    				$scope.brac.selectedAreaId = hierarchy[2];
    				$scope.brac.getBranchList($scope.brac.selectedDistrictId);
    				
    				$scope.brac.selectedBranchId = hierarchy[3];    				
    			}else if(hierarchy.length == 4){
    				$scope.brac.selectedRegionId = hierarchy[1];
    				$scope.brac.getAreaList($scope.brac.selectedRegionId);
    				
    				$scope.brac.selectedAreaId = hierarchy[2];
    				$scope.brac.getBranchList($scope.brac.selectedDistrictId);
    			}else if(hierarchy.length == 3){
    				$scope.brac.selectedRegionId = hierarchy[1];
    				$scope.brac.getAreaList($scope.brac.selectedRegionId);
    			}
    		}
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        LocationResource.getLocationById({id:locationId}, successCallback, errorCallback);
    };
    

    /**
     * get selected region id
     */
    LocationResource.getSelectedRegionId = function(){
    	return $scope.brac.selectedRegionId;
    };
    
    
    /**
     * get selected region hierarchy id
     */
    LocationResource.getSelectedRegionHierarchyId = function(regionId){
    	for(i in $scope.brac.regionList){
    		if($scope.brac.regionList[i].id === regionId){
    			return $scope.brac.regionList[i].hierarchyId;
    		}
    	}
    	return "";
    };
    
    
    /**
     * get selected Area id
     */
    LocationResource.getSelectedAreaId = function(){
    	return $scope.brac.selectedAreaId;
    };
    
    
    /**
     * get selected area hierarchy id
     */
    LocationResource.getSelectedAreaHierarchyId = function(areaId){
    	for(i in $scope.brac.areaList){
    		if($scope.brac.areaList[i].id === areaId){
    			return $scope.brac.areaList[i].hierarchyId;
    		}
    	}
    	return "";
    };
    
    /**
     * get selected branch id
     */
    LocationResource.getSelectedBranchId = function(){
    	return $scope.brac.selectedBranchId;
    };
    
    
    /**
     * get selected branch hierarchy id
     */
    LocationResource.getSelectedBranchHierarchyId = function(branchId){
    	for(i in $scope.brac.branchList){
    		if($scope.brac.branchList[i].id === branchId){
    			return $scope.brac.branchList[i].hierarchyId;
    		}
    	}
    	return "";
    };
    
    
    /**
     * get selected location id
     */
    LocationResource.getSelectedLocationId = function(){
    	var selectedLocationId = "";
        if($scope.brac.selectedBranchId !== ''){
        	selectedLocationId = $scope.brac.selectedBranchId; 
        }else if($scope.brac.selectedAreaId !== ''){
        	selectedLocationId = $scope.brac.selectedAreaId; 
        }else if($scope.brac.selectedRegionId !== '' && $scope.brac.selectedRegionId !== 'NULL'){
        	selectedLocationId = $scope.brac.selectedRegionId; 
        }
    	return selectedLocationId;
    };
    
    
    /**
     * get selected location hierarchy id
     */
    LocationResource.getSelectedLocationHierarchyId = function(){
    	var selectedLocationHierarchyId = "";
        if($scope.brac.selectedBranchId !== ''){
        	selectedLocationHierarchyId = LocationResource.getSelectedBranchHierarchyId($scope.brac.selectedBranchId);
        }else if($scope.brac.selectedAreaId !== ''){
        	selectedLocationHierarchyId = LocationResource.getSelectedAreaHierarchyId($scope.brac.selectedAreaId);
        }else if($scope.brac.selectedRegionId !== '' && $scope.brac.selectedRegionId !== 'NULL'){
        	selectedLocationHierarchyId = LocationResource.getSelectedRegionHierarchyId($scope.brac.selectedRegionId);
        }
    	return selectedLocationHierarchyId;
    };
    
    /**
     * get selected location type
     */
    LocationResource.getSelectedLocationType = function(){
    	var selectedLocationType = "";
        if($scope.brac.selectedBranchId !== ''){
        	selectedLocationType = 'branch'; 
        }else if($scope.brac.selectedAreaId !== ''){
        	selectedLocationType = 'area'; 
        }else if($scope.brac.selectedRegionId !== '' && $scope.brac.selectedRegionId !== 'NULL'){
        	selectedLocationType = 'region'; 
        }
    	return selectedLocationType;
    };
    
    /**
     * check availability need for showing in display
     */
    $scope.brac.checkAvailability = function(locationType) {
    	if($rootScope.loogedUser.username !== 'admin'){
			if($scope.brac.visibilityScope.length==5){
				return true;
			}
			if($scope.brac.visibilityScope.length==4){
				if(locationType === 'region' || locationType === 'area' ){
					return true;
				}
			}
			
			if($scope.brac.visibilityScope.length==3){
				if(locationType === 'region' ){
					return true;
				}
			}
    	}
		return false;		
	}
    
    
    /**
     * Populate logged user location
     */
    LocationResource.setLoggedUserLocation = function(locationHierarchy) {
    	if(locationHierarchy !== ''){
			var hierarchy = locationHierarchy.split(">");
			if(hierarchy.length == 5){
				$scope.brac.selectedRegionId = hierarchy[1];
				$scope.brac.getAreaList($scope.brac.selectedRegionId);
				
				$scope.brac.selectedAreaId = hierarchy[2];
				$scope.brac.getBranchList($scope.brac.selectedDistrictId);
				
				$scope.brac.selectedBranchId = hierarchy[3];    				
			}else if(hierarchy.length == 4){
				$scope.brac.selectedRegionId = hierarchy[1];
				$scope.brac.getAreaList($scope.brac.selectedRegionId);
				
				$scope.brac.selectedAreaId = hierarchy[2];
				$scope.brac.getBranchList($scope.brac.selectedDistrictId);
			}else if(hierarchy.length == 3){
				$scope.brac.selectedRegionId = hierarchy[1];
				$scope.brac.getAreaList($scope.brac.selectedRegionId);
			}
		}
    };
    
    $scope.brac.getRegionList();
    if($rootScope.loogedUser.username !== 'admin'){
    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
		$scope.brac.visibilityScope = userHierarchy.split(">");
		LocationResource.setLoggedUserLocation(userHierarchy);
    }
});