app.registerCtrl('DetailTransactionHistoryController', function($scope, $http, TransactionHistoryResource, $routeParams ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    $scope.monthText=["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    $scope.numberOfPages = function() {
        var result = Math.ceil($scope.totalSize/$scope.pageSize);
        var max = (result == 0) ? 1 : result;
        $scope.pageRange = [];
        
        var ctr = 0, ctrMax = 10;
        if($scope.totalSize < 10){
        	ctrMax = max;
        }
        if($scope.currentPage > 6){
        	ctr = $scope.currentPage - 1;
        	ctrMax = ctr + 10; 
        }
        for(var ctr; ctr<ctrMax; ctr++) {
            $scope.pageRange.push(ctr);
        }
        return max;
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
        
        TransactionHistoryResource.listByTransactionId({start: $scope.currentPage, max : $scope.pageSize, transactionId:$routeParams.TransactionId}, successCallback, errorCallback);
        
    };
    
    $scope.clearSearch = function() {
    	$scope.search.feeId = "";
    	$scope.search.instituteId = "";
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
       if($scope.currentPage < ($scope.numberOfPages() - 1) ) {
           $scope.currentPage++;
           $scope.performSearch();
       }
    };
    
    $scope.setPage = function(n) {
       $scope.currentPage = n;
       $scope.performSearch();
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
                TransactionHistoryResource.remove({TransactionHistoryId: id}, successCallback, errorCallback);
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
    	$scope.search.name = $routeParams.feeId;
    }
    
    if (typeof $routeParams.desc !== "undefined") {
    	$scope.search.description = $routeParams.instituteId;
    }
    
    $scope.performSearch();
});