app.registerCtrl('SearchTransactionHistoryController', function($scope, $http, TransactionHistoryResource, $routeParams, LocationResource, GradeResource, StudentResource, 
																InstituteResource, $rootScope, PagerService) 
{

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.instituteDisable=true;
    
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.studentList=[];
    $scope.locationHierarchy=null;
    
    $scope.totalSize = 0;
    $scope.pagerService = PagerService;
    $scope.maxPage = 0;

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
        
        TransactionHistoryResource.queryAll(
	        									{
	        										start: 				$scope.currentPage, 
	        										max : 				$scope.pageSize,         										
	        										locationHierarchy:	$scope.locationHierarchy,
	        										instituteId: 		$scope.search.instituteId,
	        										gradeId:			$scope.search.gradeId,
	        										studentId:			$scope.search.studentId,
	        										collectionDate:		$scope.search.collectionDate,
	        										month:				$scope.search.month
	        										
	        									},
	        									successCallback, errorCallback
        									);
    };
    
    $scope.clearSearch = function() { 
    	$scope.instituteList = [];
    	$scope.gradeList=[];
    	$scope.studentList=[];
    	
		$scope.locationHierarchy= null;
		$scope.search.instituteId='';
		$scope.search.gradeId='';
		$scope.search.studentId='';
		$scope.search.collectionDate='';
		$scope.search.month='';
		LocationResource.unBindLocation();
		
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
    
    $scope.getGradeList = function(instituteId) {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.listByInstituteId({InstituteId: instituteId}, successCallback, errorCallback);
	};
	
	$scope.getStudentList = function(instituteId, gradeId) {
		var successCallback = function(data, responseHeaders) {
			$scope.studentList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		StudentResource.listBy({instituteId: instituteId, gradeId: gradeId}, successCallback, errorCallback);
	};
	
	// Load institute by location hierarchy =============================================================================
    $scope.getInstituteList = function(locationHierarchy) {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
			$scope.instituteDisable = false;
		};
		var errorCallback = function() {
			console.log('error');
		};		
		InstituteResource.hierarchy({id : locationHierarchy}, successCallback, errorCallback);
    };
    //===================================================================================================================
        
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	console.log("See watchValue: " + newValue);
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.locationHierarchy);
    	}
    	else
    		$scope.locationHierarchy=null;
    });
   //===============================================================================================
    
    if (typeof $routeParams.start !== "undefined") {
    	$scope.currentPage = $routeParams.start;
    }
    
    if (typeof $routeParams.max !== "undefined") {
    	$scope.pageSize = $routeParams.max;
    }
    
    $scope.onInstituteChange = function(instituteId) {
    	$scope.getInstituteDetails(instituteId);
    	$scope.getGradeList(instituteId);
    }
	
    $scope.getInstituteDetails = function(instituteId) {
		var successCallback = function(data) {
			$scope.search.gradeId = data.model.currentGradeId;
			$scope.getStudentList($scope.search.instituteId, $scope.search.gradeId);
		};

		var errorCallback = function() {
		};
		InstituteResource.get({InstituteId : instituteId}, successCallback, errorCallback);
	};
    
        
    $scope.performSearch();    
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
});