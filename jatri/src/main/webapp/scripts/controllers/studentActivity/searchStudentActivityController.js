app.registerCtrl('SearchStudentActivityController', function($scope, $http, StudentActivityResource, $routeParams, PagerService) 
{

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

    $scope.performSearch = function() 
    {
    	var successCallback = function(data,responseHeaders) 
    	{
    		$scope.searchResults = data.model;
    		$scope.totalSize = data.total;
    		$scope.numberOfPages();			
        };
        var errorCallback = function() 
        {
        	console.log('error');
        }; 
        
        StudentActivityResource.queryAll({start: $scope.currentPage, max : $scope.pageSize, instituteId: $scope.search.instituteId, result: $scope.search.result},successCallback, errorCallback);
    };
    
    $scope.clearSearch = function() 
    {
    	$scope.search.instituteId = "";
    	$scope.search.result = "";
    };
    
    $scope.searchButtonListener = function() 
    {
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
    
    $scope.remove = function(name, id) 
    {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) 
            {
            	var successCallback = function(data) 
            	{
        			if(data.success = '1')
        			{
        				$.growl.notice({ message: "Deleted successfully!" });
        				$scope.performSearch();
        			}
        			else
        			{
        				$scope.displayError = true;
        			}
                };
                
                var errorCallback = function() 
                {
                    //$scope.displayError=true;
                }; 
                StudentActivityResource.remove({StudentActivityId: id}, successCallback, errorCallback);
            },
            
            cancel: function(button) 
            {
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
    
    if (typeof $routeParams.instituteId !== "undefined") {
    	$scope.search.instituteId = $routeParams.instituteId;
    }
    
    if (typeof $routeParams.result !== "undefined") {
        $scope.search.result = $routeParams.result;
    }
    
    $scope.performSearch();
});