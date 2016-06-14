app.registerCtrl('SearchIncomeController', function($scope, $http, IncomeResource, $routeParams, PagerService, LocationResource, UdvResource ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    $scope.pagerService = PagerService;
    $scope.maxPage = 0;
    $scope.indicators = [];
    $scope.udvList=[];
    $scope.monthText=["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    
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
        
        IncomeResource.queryAll({start: $scope.currentPage, max : $scope.pageSize, locationHierarchy: $scope.search.locationHierarchy, 
        						indicatorUdvId: $scope.search.indicatorUdvId, month: $scope.search.month, year: $scope.search.year, verified: $scope.search.verified},
        							    successCallback, errorCallback);
    };
    
    $scope.clearSearch = function() {
    	$scope.search.month = null;
    	$scope.search.year = null;
    };
    
    $scope.searchButtonListener = function() {
    	$scope.currentPage = 0;
        $scope.pageSize= 10;
        $scope.search.locationHierarchy = LocationResource.getSelectedLocationId();
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

                }; 
                ExpenditureResource.remove({ExpenditureId: id}, successCallback, errorCallback);
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
    
    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
    if (typeof $routeParams.indicatorUdvId !== "undefined") {
    	$scope.search.indicatorUdvId = $routeParams.indicatorUdvId;
    }
    
    if (typeof $routeParams.locationHierarchy !== "undefined") {
    	$scope.search.locationHierarchy = $routeParams.locationHierarchy;
    }
    
    if (typeof $routeParams.month !== "undefined") {
    	$scope.search.month = $routeParams.month;
    }
    
    if (typeof $routeParams.year !== "undefined") {
    	$scope.search.year = $routeParams.year;
    }
    
    if (typeof $routeParams.verified !== "undefined") {
    	$scope.search.verified = $routeParams.verified;
    }
    
    $scope.performSearch();
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.udvList('Income indicator');
});