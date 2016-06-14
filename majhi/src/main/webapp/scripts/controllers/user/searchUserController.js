app.registerCtrl('SearchUserController', function($scope, $http, UserResource, 
		$routeParams , UdvResource, LocationResource, locationParser, rexValidation,$rootScope, PagerService, rexValidation) {
    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    $scope.udvList = [];
    $scope.search.locationId={};
    $scope.validation = rexValidation;
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
       
       UserResource.queryAll(
        						{
        							start:				$scope.currentPage, 
        							max : 				$scope.pageSize, 
        							username: 			$scope.search.username, 
        							email: 				$scope.search.email,
        							mobileNumber: 		$scope.search.mobileNumber,
        							userCategoryUdvId: 	$scope.search.userCategoryUdvId,
        							locationId: 		$scope.search.locationId,
        							locationHierarchy: 	$scope.search.locationHierarchy
        						},
        						successCallback, 
        						errorCallback
        					);
    };
    
    $scope.clearSearch = function() {
    	$scope.search.username =null;
    	$scope.search.email = null;
    	$scope.search.mobileNumber=null;
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
    
    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
    if (typeof $routeParams.email !== "undefined") {
    	$scope.search.email = $routeParams.email;
    }
    
    if (typeof $routeParams.username !== "undefined") {
    	$scope.search.username = $routeParams.username;
    }
    
    $scope.udvList('Location type, User category, Designation, Staff grade');
    setTimeout($scope.performSearch, 50) 
    //$scope.performSearch();
});