app.registerCtrl('SearchTeacherController', function($scope, $http, UserResource, $routeParams , UdvResource, 
											InstituteResource, LocationResource, locationParser, $rootScope, PagerService,rexValidation) {
    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    $scope.udvList = [];
    $scope.instituteList = [];
    
    $scope.categoryValue="Teacher";
    $scope.validation = rexValidation;
    $scope.instituteDisable=true;
    // For watch event work correctly =================
    //$scope.pageLoaded = false;
    //=================================================
    
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
        
        UserResource.listAllTeacher(
				{
					start:				$scope.currentPage, 
					max : 				$scope.pageSize, 
					username: 			$scope.search.username, 
					email: 				$scope.search.email,
					mobileNumber: 		$scope.search.mobileNumber,
					userCategoryUdvId: 	$scope.categoryValue,
					locationId: 		$scope.search.locationId,
					locationHierarchy: 	$scope.search.locationHierarchy,
					instituteId:		$scope.search.instituteId,
					dropOut:			$scope.search.dropOut
				},
				successCallback, 
				errorCallback
			);
        
        // For watch event work correctly =================
        //$scope.pageLoaded =  true;
        //=================================================
    };
    
    $scope.clearSearch = function() {
    	$scope.search.username = "";
    	$scope.search.email = "";
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
                UserResource.remove({UserId: id}, successCallback, errorCallback);
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
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
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
    //===================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.search.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.search.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.search.locationHierarchy);
    	}
    });
    //===============================================================================================
    
    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
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
    //$scope.getInstituteList();
    $scope.udvList('Location type, User category, Designation, Staff grade');
    setTimeout($scope.performSearch, 50)
    //$scope.performSearch();
});