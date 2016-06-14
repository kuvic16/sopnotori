app.registerCtrl('IncomeReportController', function($scope, $http, IncomeResource, $routeParams, PagerService, LocationResource, UdvResource ) {

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
    $scope.months = [];
    $scope.udvList=[];
    
    $scope.headOfAccountList = [];
    $scope.expenditureTypeList = [];
    
    $scope.monthText=["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    
    $scope.clearSearch = function() {
    	$scope.search.month = null;
    	$scope.search.year = null;
    };
    
    $scope.searchButtonListener = function() {
    	$scope.search.locationHierarchy = LocationResource.getSelectedLocationId();
    	$scope.report();
    };
    
    
    $scope.report = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.indicators = data.model.indicatorModels;
    		$scope.months = data.model.months;
        };
        var errorCallback = function() {
        	console.log('error');
        };        
        IncomeResource.report({
	        	locationHierarchy: $scope.search.locationHierarchy, 
	        	indicatorUdvId: $scope.search.indicatorUdvId, 
	        	month: $scope.search.month, 
	        	year: $scope.search.year, 
	        	verified: $scope.search.verified
        	},successCallback, errorCallback);
    };
    
    $scope.print = function() {
    	alert("asdjvs");
    };
    
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
    
    /**
     * load income indicator list
     */
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
    $scope.report();
});