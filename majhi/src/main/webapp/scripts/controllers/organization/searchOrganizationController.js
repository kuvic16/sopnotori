app.registerCtrl('SearchOrganizationController', function($scope, $http, OrganizationResource,$routeParams, PagerService ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
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
        
        OrganizationResource.queryAll
        (
        		{
        			start:	$scope.currentPage, 
        			max :	$scope.pageSize, 
        			name:	$scope.search.name, 
        			code:	$scope.search.code, 
        			email:	$scope.search.emailId 
        		},
        		successCallback, errorCallback
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
    
    $scope.remove = function(organization_name, organization_id) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + organization_name + "</b>?",
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
                OrganizationResource.remove({OrganizationId: organization_id}, successCallback, errorCallback);
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
    
    if (typeof $routeParams.name !== "undefined") {
    	$scope.search.name = $routeParams.name;
    }
    
    if (typeof $routeParams.desc !== "undefined") {
    	$scope.search.description = $routeParams.desc;
    }
    
    $scope.performSearch();
});