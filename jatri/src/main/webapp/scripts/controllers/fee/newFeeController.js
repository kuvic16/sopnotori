app.registerCtrl('NewFeeController', function ($scope, $location, locationParser, FeeResource , UdvResource, InstituteResource, GradeResource, rexValidation, LocationResource, $rootScope) 
{
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.fee = $scope.fee || {};
    $scope.udvList = [];
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.validation = rexValidation;
    $scope.instituteDisable=true;
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		if(data.success === "1"){
	    		$.growl.notice({ message: "Added successfully!" });
	            $location.path("/Fee");
	            $scope.displayError = false;
    		}else{
    			$.growl.error({ message: data.message });
    		}
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        console.log($scope.fee);
        FeeResource.save($scope.fee, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Fee");
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
    //===================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.fee.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.fee.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.fee.locationHierarchy);
    	}
    });
   //===============================================================================================
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.onInstituteChange = function(instituteId) {
    	$scope.getInstituteDetails(instituteId);
    	$scope.getGradeList(instituteId);
    }
    
    $scope.getGradeList = function(instituteId) {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.listByInstituteId({InstituteId: instituteId}, successCallback, errorCallback);
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
	$scope.udvList('Fee type,Fee category');
});