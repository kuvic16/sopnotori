app.registerCtrl('FeeEngineController', function ($scope, $location, locationParser, FeeResource , UdvResource, InstituteResource, GradeResource, rexValidation, LocationResource, $rootScope) 
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
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Fee");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        console.log($scope.fee);
        FeeResource.engine($scope.fee, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Fee");
    };
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.fee.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.fee.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
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
    
	$scope.getGradeList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.queryAll({all: true}, successCallback, errorCallback);
	};
	
	$scope.getFeeTypeUdvDetails = function(feeTypeUdvId) {
		var successCallback = function(data){
    		$scope.fee.code = data.model.altValue;
        };
        var errorCallback = function() {
            
        };
        UdvResource.get({UdvId:feeTypeUdvId}, successCallback, errorCallback);
	};
	
//	$scope.loadInitInstitute = function(){
//		var userHierarchy = $rootScope.loogedUser.locationHierarchy;
//		if($rootScope.loogedUser.username !== 'admin'){
//	    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
//	    	var hierarchy = userHierarchy.split(">");
//			if(hierarchy.length == 5){
//				$scope.getInstituteList(userHierarchy);
//			}
//	    }
//	};
	
	//$scope.loadInitInstitute();
	$scope.udvList('Location type, Fee type,Fee category');
	$scope.getGradeList();
});