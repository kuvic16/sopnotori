app.registerCtrl('EditInstituteController', function(
														$scope, 
														$routeParams,
														$location, 
														InstituteResource, 
														UdvResource, 
														OrganizationResource,
														DonorResource, 
														UserResource, 
														EducationTypeResource, 
														LocationResource,
														rexValidation,
														$rootScope
													) 
{
	
	var self = this;
	$scope.disabled = false;
	$scope.$location = $location;
	$scope.validation = rexValidation; ///^\d*$/;
	$scope.institute = {};
	$scope.udvList = [];
	$scope.organizationList = [];
	$scope.donorList = [];
	$scope.userList = [];
	$scope.institute.poId = '';
	$scope.educationTypeList = [];
	$scope.selectedEducationType = { 
		educationType: []
	};


	$scope.get = function() {
		var successCallback = function(data) {
			self.original = data.model;
			$scope.institute = new InstituteResource(self.original);
			$scope.institute.numberOfShift= parseInt($scope.institute.numberOfShift);
			$scope.institute.totalBoys = parseInt($scope.institute.totalBoys);
			$scope.institute.totalGirls = parseInt($scope.institute.totalGirls);
			$scope.institute.totalEthnic = parseInt($scope.institute.totalEthnic);
			$scope.institute.totalCsn = parseInt($scope.institute.totalCsn);
			$scope.institute.totalStudent = parseInt($scope.institute.totalStudent);
			
			if($scope.institute.locationId !== ''){
				LocationResource.setLocation($scope.institute.locationId);
            }
			
			$scope.getEducationTypeByInstituteId($scope.institute.id);
			
			if (angular.isUndefined($scope.institute.organizationId) || $scope.institute.organizationId === null) {
				$scope.institute.organizationId = "";
			}

			if (angular.isUndefined($scope.institute.donorId) || $scope.institute.donorId === null) {
				$scope.institute.donorId = "";
			}

			console.log("PO ID: " + $scope.institute.poId);
			if (angular.isUndefined($scope.institute.poId) || $scope.institute.poId === null) {
				$scope.institute.poId = '';
			}
			else
				$scope.getUserList($scope.institute.locationHierarchy);
			
			$('#sessionStart').datepicker('setDate',$scope.institute.sessionStart);
			$('#sessionEnd').datepicker('setDate',$scope.institute.sessionEnd);
			$('#openingDate').datepicker('setDate',$scope.institute.openingDate);

		};

		var errorCallback = function() {
			$location.path("/Institute");
		};
		InstituteResource.get({InstituteId : $routeParams.InstituteId}, successCallback, errorCallback);
	};
	
	// end of get()

	$scope.isClean = function() {
		return angular.equals(self.original, $scope.institute);
	};

	$scope.save = function() {
		var successCallback = function() {
			$.growl.notice({ message: "Updated successfully!" });
			$location.path("/Institute");
			//$scope.get();
			$scope.displayError = false;
		};
		
		var errorCallback = function() {
			$scope.displayError = true;
		};
		
		$scope.institute.locationId = LocationResource.getSelectedLocationId();
        $scope.institute.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
		$scope.institute.educationTypeModelList = $scope.selectedEducationType.educationType;
		$scope.institute.$update(successCallback, errorCallback);
	};
	// end of save()
	
	$scope.cancel = function() {
		$location.path("/Institute");
	};
	// end of cancel()
	
	$scope.remove = function(name) {
		$.confirm({
			text : "Are you sure to delete <b>" + name + "</b>?",
			confirm : function(button) {
				var successCallback = function(data) {
					if (data.success = '1') {
						$.growl.notice({
							message : "Deleted successfully!"
						});
						$location.path("/Institute");
						$scope.displayError = false;
					} else {
						$scope.displayError = true;
					}
				};

				var errorCallback = function() {
					$scope.displayError = true;
				};

				$scope.institute.$remove(successCallback, errorCallback);
			},
			cancel : function(button) {
				return false;
			},
			confirmButton : "Yes",
			cancelButton : "Nope",
			confirmButtonClass : "btn-danger",
			cancelButtonClass : "btn-info"
		});
	};
	// end of remove()
	
	$scope.getUdvList = function(cateogryNames, callback) {
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
		OrganizationResource.queryAll({all : true}, successCallback, errorCallback);
	};

	$scope.getDonorList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.donorList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		DonorResource.queryAll({all : true}, successCallback, errorCallback);
	};

	/*$scope.getUserList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.userList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		UserResource.queryAll({category : 'po'}, successCallback, errorCallback);
	};*/
	
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

	
	$scope.getEducationTypeList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.educationTypeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		EducationTypeResource.list(successCallback, errorCallback);
	};
	
    $scope.getEducationTypeByInstituteId = function(institueId) {
        var successCallback = function(data){
        	// Copy the selected object in education type array
            $scope.selectedEducationType.educationType = angular.copy(data.model);
        };
        var errorCallback = function() {
            console.log('error');
        };
        EducationTypeResource.listByInstituteId({InstituteId: institueId}, successCallback, errorCallback);
    };
    
    /*$scope.loadInitPo = function(){
		var userHierarchy = $rootScope.loogedUser.locationHierarchy;
		if($rootScope.loogedUser.username !== 'admin'){
	    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
	    	var hierarchy = userHierarchy.split(">");
			if(hierarchy.length == 5){
				$scope.getUserList(userHierarchy);
			}
	    }
	};*/
    
    $scope.get();
	$scope.getUdvList('Location type, Institute type, Project code');
	$scope.getOrganizationList();
	$scope.getDonorList();
	//$scope.getUserList($scope.institute.locationHierarchy);
	$scope.getEducationTypeList();

});