app.registerCtrl('SearchInstituteController', function($scope, $http, InstituteResource, $routeParams, UdvResource, UserResource,
															  EducationTypeResource, LocationResource, $rootScope, PagerService) 
{
    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    
    $scope.userList = [];
    $scope.programOrganizerDisable = true;
    $scope.udvList = [];
    $scope.educationTypeList = [];
    
    $scope.pagerService = PagerService;
    $scope.maxPage = 0;
    
    $scope.numberOfPages = function() {
        pager = PagerService.getPager($scope.totalSize, $scope.pageSize, $scope.currentPage);
        $scope.pageRange = pager.pageRange;
        $scope.maxPage = pager.maxPage;
    };

    $scope.performSearch = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.searchResults = data.model;
    		$scope.totalSize = data.total;
    		$scope.numberOfPages();		
    		
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        
        
        $scope.search.locationId = LocationResource.getSelectedLocationId();
        $scope.search.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        
        InstituteResource.queryAll
        						(
        							{
        								start: 				$scope.currentPage, 
        								max : 				$scope.pageSize, 
        								name: 				$scope.search.name, 
        								code: 				$scope.search.code,
        								instituteTypeUdvId:	$scope.search.instituteTypeUdvId,
        								educationTypeId:	$scope.search.educationTypeId,
        								poId:				$scope.search.poId,
        								locationTypeUdvId:	$scope.search.locationTypeUdvId,
        								projectCodeUdvId:	$scope.search.projectCodeUdvId,
        								locationId: 		$scope.search.locationId,
            							locationHierarchy: 	$scope.search.locationHierarchy
        							}, 
        							successCallback, 
        							errorCallback
        						);
    };
    
    $scope.clearSearch = function() {
    	$scope.search.name = "";
    	$scope.search.code = "";
    };
    
    $scope.searchButtonListener = function() {
    	$scope.currentPage = 0;
        $scope.pageSize= 10;
    	$scope.performSearch();
    };
    
    $scope.previous = function() {
        if($scope.currentPage > 0) {
            $scope.currentPage--;
            $scope.performSearch();
        }
     };
     
     $scope.next = function() {
        if($scope.currentPage < ($scope.maxPage - 1) ) {
            $scope.currentPage++;
            $scope.performSearch();
        }
     };
     
     $scope.setPage = function(n) {
     	if($scope.currentPage != n){
 	       $scope.currentPage = n;
 	       $scope.performSearch();
     	}
     };
    
    $scope.remove = function(name, id) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
        			if(data.success = '1'){
        				 $.growl.notice({ message: "Deleted successfully!" });
        				$scope.performSearch();
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    //$scope.displayError=true;
                }; 
                InstituteResource.remove({InstituteId: id}, successCallback, errorCallback);
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
		var successCallback = function(data, responseHeaders) {
			$scope.udvList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		UdvResource.udvList({CategoryNames : cateogryNames}, successCallback, errorCallback);
	};
	
//================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.userList = [];
    	$scope.search.poId = '';
    	$scope.programOrganizerDisable = true;
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
			$scope.programOrganizerDisable = false;
		};
		var errorCallback = function() {
			console.log('error');
		};
		UserResource.queryAll({category : 'po', locationHierarchy: locationHierarchy }, successCallback, errorCallback);
	};
	

    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
        
    $scope.getUdvList('Location type, Institute type, Project code');
    $scope.getEducationTypeList();
   // $scope.getUserList();
    //$scope.performSearch();
    setTimeout($scope.performSearch, 50) 
});