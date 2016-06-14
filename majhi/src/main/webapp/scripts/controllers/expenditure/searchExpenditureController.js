app.registerCtrl('SearchExpenditureController', function($scope, $http, ExpenditureResource, $routeParams, PagerService, LocationResource, UdvResource ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    $scope.pagerService = PagerService;
    $scope.maxPage = 0;
    
    $scope.headOfAccountList = [];
    $scope.expenditureTypeList = [];
    
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
        
        ExpenditureResource.queryAll({start: $scope.currentPage, max : $scope.pageSize, expenditureTypeUdvId: $scope.search.expenditureTypeUdvId,        								
        							    headsOfAccountUdvId:$scope.search.headsOfAccountUdvId,	
        							    locationHierarchy: $scope.search.locationHierarchy, year: $scope.search.year, month: $scope.search.month},
        							    successCallback, errorCallback);
    };
    
    $scope.clearSearch = function() {
    	$scope.search.feeId = "";
    	$scope.search.instituteId = "";
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
    
    $scope.getHeadOfAccountList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.headOfAccountList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.getExpenditureTypeList = function(cateogryName, parentId) {
    	var successCallback = function(data, responseHeaders) {
    		$scope.expenditureTypeList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvListByParentId({categoryName:cateogryName, parentId:parentId},successCallback, errorCallback);
    };
    
    $scope.onHeadOfAccountChange = function(headsOfAccountId){
    	$scope.getExpenditureTypeList("Expenditure type", headsOfAccountId);
    };
    
    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
    if (typeof $routeParams.expenditureTypeUdvId !== "undefined") {
    	$scope.search.expenditureTypeUdvId = $routeParams.expenditureTypeUdvId;
    }
    
    if (typeof $routeParams.headsOfAccountUdvId !== "undefined") {
    	$scope.search.headsOfAccountUdvId = $routeParams.headsOfAccountUdvId;
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
    
    $scope.performSearch();
    $scope.getHeadOfAccountList('Heads of Accounts');
});