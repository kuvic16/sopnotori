	app.registerCtrl('NewInstituteController', function
														(
															$scope, 
															$location, 
															locationParser, 
															InstituteResource, 
															UdvResource, 
															OrganizationResource,
															DonorResource, 
															UserResource, 
															LocationResource, 
															EducationTypeResource,
															$rootScope
														) 
{
		
		
	$scope.disabled = false;
	$scope.$location = $location;
	$scope.institute = $scope.institute || {};
	
	//$scope.sumTotalStudent = 0;
	
	$scope.udvList = [];
	$scope.organizationList = [];
	$scope.donorList = [];
	$scope.userList = [];
	
	$scope.educationTypeList = [];
	
	$scope.selectedEducationType = { 
		educationType: []
	};

	//$scope.sumTotalStudent = $scope.institute.totalBoys + $scope.institute.totalGirls;
	//$scope.institute.totalStudent = parseInt($scope.sumTotalStudent);
	
	$scope.save = function() {
		var successCallback = function(data, responseHeaders) {
			//var id = data.model.id;
			$.growl.notice({ message: "Added successfully!" });
			$location.path("/Institute");
			$scope.displayError = false;
		};
		var errorCallback = function() {
			$scope.displayError = true;
		};
		
		$scope.institute.locationId = LocationResource.getSelectedLocationId();
        $scope.institute.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
		$scope.institute.educationTypeModelList = $scope.selectedEducationType.educationType;
		InstituteResource.save($scope.institute, successCallback, errorCallback);
	};

	$scope.cancel = function() {
		$location.path("/Institute");
	};

	$scope.getUdvList = function(cateogryNames) {
		var successCallback = function(data, responseHeaders) {
			$scope.udvList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		UdvResource.udvList({CategoryNames : cateogryNames}, successCallback, errorCallback);
	};

	$scope.getOrganizationList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.organizationList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		OrganizationResource.queryAll({
			all : true
		}, successCallback, errorCallback);
	};

	$scope.getDonorList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.donorList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		DonorResource.queryAll({
			all : true
		}, successCallback, errorCallback);
	};

	$scope.getUserList = function(locationHierarchy) {
		var successCallback = function(data, responseHeaders) {
			console.log(data.model);
			$scope.userList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		UserResource.queryAll({category : 'po', locationHierarchy: locationHierarchy }, successCallback, errorCallback);
	};

	$scope.getEducationTypeList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.educationTypeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		EducationTypeResource.list(successCallback, errorCallback);
	};
	
	
//================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.userList = [];
    	$scope.institute.poId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getUserList($scope.locationHierarchy);
    	}
    });
  //===============================================================================================

	
	$scope.getUdvList('Location type, Institute type, Project code');
	$scope.getOrganizationList();
	$scope.getDonorList();
	$scope.getEducationTypeList();
	
	// load PO list according to location if user is not admin 
	if($rootScope.loogedUser.username !== 'admin'){
    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
    	$scope.getUserList(userHierarchy);
    }
});