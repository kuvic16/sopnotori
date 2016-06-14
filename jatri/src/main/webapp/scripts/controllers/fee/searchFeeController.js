app.registerCtrl('SearchFeeController', function($scope, $http, FeeResource, $routeParams, UdvResource, InstituteResource, GradeResource, LocationResource, $rootScope, 
		PagerService, rexValidation){

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    
    $scope.udvList = [];
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.validation = rexValidation;

    $scope.totalSize = 0;
    
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
        
        FeeResource.queryAll(
        						{
        							start: 				$scope.currentPage, 
        							max :				$scope.pageSize, 
        							feeTypeUdvId:		$scope.search.feeTypeUdvId,
        							feeCategoryUdvId:	$scope.search.feeCategoryUdvId,
        							instituteId:		$scope.search.instituteId,
        							code:				$scope.search.code,
        							gradeId:			$scope.search.gradeId,
        							year:				$scope.search.year,
        							amount:				$scope.search.amount,
        							mandatory:			$scope.search.mandatory
        							
        						},
        						successCallback, 
        						errorCallback
        					);
        // For watch event work correctly =================
        //$scope.pageLoaded =  true;
        //=================================================
    };
    
    $scope.clearSearch = function() {
    	$scope.search.instituteId = "";
    	$scope.search.gradeId = "";
    	$scope.search.feeTypeUdvId="";
    	$scope.search.feeCategoryUdvId="";
    	$scope.search.code=null;
    	$scope.search.mandatory="";
    	$scope.instituteList = [];
    	$scope.search.instituteId="";
    	$scope.search.amount=null;
    	$scope.search.year=null;
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
                FeeResource.remove({FeeId: id}, successCallback, errorCallback);
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
    
    
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
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
     
    
    $scope.getGradeList = function(instituteId) {
		var successCallback = function(data, responseHeaders) {
			$scope.gradeList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		GradeResource.listByInstituteId({InstituteId: instituteId}, successCallback, errorCallback);
	};
       
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
	
	$scope.applyToAllStudent = function(){
		$.confirm({
    		text: "Are you sure to apply fees to all student?",
            confirm: function(button) {
            	$("#emisLoader").show();
        		var successCallback = function(data,responseHeaders){
        			$("#emisLoader").hide();
            		$.growl.notice({ message: "Applied to students!" });    		
                };
                var errorCallback = function() {
                	$("#emisLoader").hide();
                	$.growl.notice({ message: "Failed!" });
                };
                FeeResource.apply(successCallback, errorCallback);
            },
            cancel: function(button) {
            	return false;
            },
            confirmButton: "Yes",
            cancelButton: "Nope",
            confirmButtonClass: "btn-danger",
            cancelButtonClass: "btn-info"
        });
	}
	
	$scope.onInstituteChange = function(instituteId) {
    	$scope.getInstituteDetails(instituteId);
    	$scope.getGradeList(instituteId);
    }
	
	$scope.getInstituteDetails = function(instituteId) {
		var successCallback = function(data) {
			$scope.search.gradeId = data.model.currentGradeId;
		};

		var errorCallback = function() {
		};
		InstituteResource.get({InstituteId : instituteId}, successCallback, errorCallback);
	};
		
	$scope.loadInitInstitute();
	$scope.udvList('Fee type,Fee category');
	setTimeout($scope.performSearch, 50) 
});