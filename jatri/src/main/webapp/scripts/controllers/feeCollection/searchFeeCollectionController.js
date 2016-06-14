app.registerCtrl('SearchFeeCollectionController', function($scope, $http, FeeCollectionResource,$routeParams, LocationResource, InstituteResource, 
															GradeResource, StudentResource, $rootScope) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalSize = 0;
    
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.studentList=[];
    
    $scope.breadcrumbs = [];
    $scope.breadcrumb = {};
    
    
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
        	if(ctrMax > max) {
        		ctrMax = max
        	}
        }
        for(var ctr; ctr<ctrMax; ctr++) {
        	if(ctr>=result)
        		break;
            $scope.pageRange.push(ctr);
        }
        return max;
    };
    
    
    /**
     * initial searching
     */
    $scope.initSearch = function() {
    	$scope.search.init = true;
    	$scope.performSearch($scope.search);
    };
    
    /**
     * next level searching
     */
    $scope.goNextLevel = function(searchResultId, nextSearchType, searchResultName) {
    	$scope.search.init = false;
    	if(nextSearchType == 'SEARCH_TYPE_AREA'){
    		$scope.search.regionId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_BRANCH'){
    		$scope.search.areaId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_INSTITUTE'){
    		$scope.search.branchId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_GRADE'){
    		$scope.search.instituteId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_STUDENT'){
    		$scope.search.gradeId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_STUDENT_FEE'){
    		$scope.search.studentId = searchResultId;
    	}else if(nextSearchType == 'SEARCH_TYPE_STUDENT_FEE_HISTORY'){
    		$scope.search.studentFeeId = searchResultId;
    	}
    	
    	$scope.search.searchTypeId = searchResultId;
    	$scope.search.searchType = nextSearchType;
    	
    	$scope.pushBreadcrumbs(searchResultId, nextSearchType, searchResultName);
    	$scope.performSearch($scope.search);
    };
    
    $scope.pushBreadcrumbs = function(searchResultId, nextSearchType, searchResultName){
    	$scope.breadcrumb = {};
    	$scope.breadcrumb.searchResultId = searchResultId;
    	$scope.breadcrumb.nextSearchType = nextSearchType;
    	$scope.breadcrumb.searchResultName = searchResultName;
    	
    	$scope.breadcrumbs.push($scope.breadcrumb);
    	
    	nBreadcrumbs = [];
    	for(i in $scope.breadcrumbs){
    		nBreadcrumbs.push($scope.breadcrumbs[i]);
    		if($scope.breadcrumbs[i].searchResultId === searchResultId){
    			break;
    		}
    	}
    	
    	$scope.breadcrumbs = nBreadcrumbs; 
    }
    
    
    
    /**
     * custom searching
     */
    $scope.doSearch = function() {
    	$scope.search.init = true;
    	if(!$scope.isUndefinedOrNull($scope.search.studentId)){
    		console.log($scope.search.studentId);
    		$scope.search.searchTypeId = $scope.search.studentId;
        	$scope.search.searchType = 'SEARCH_TYPE_STUDENT_FEE';
        	$scope.search.init = false;
    	}else if(!$scope.isUndefinedOrNull($scope.search.gradeId)){
    		$scope.search.searchTypeId = $scope.search.gradeId;
        	$scope.search.searchType = 'SEARCH_TYPE_STUDENT';
        	$scope.search.init = false;
    	}else if(!$scope.isUndefinedOrNull($scope.search.instituteId)){
    		$scope.search.searchTypeId = $scope.search.instituteId;
        	$scope.search.searchType = 'SEARCH_TYPE_GRADE';
        	$scope.search.init = false;
    	}else if(!$scope.isUndefinedOrNull(LocationResource.getSelectedBranchId())){
    		$scope.search.searchTypeId = LocationResource.getSelectedBranchId();
        	$scope.search.searchType = 'SEARCH_TYPE_INSTITUTE';
        	$scope.search.init = false;
    	}else if(!$scope.isUndefinedOrNull(LocationResource.getSelectedAreaId())){
    		$scope.search.searchTypeId = LocationResource.getSelectedAreaId();
        	$scope.search.searchType = 'SEARCH_TYPE_BRANCH';
        	$scope.search.init = false;
    	}else if(!$scope.isUndefinedOrNull(LocationResource.getSelectedRegionId())){
    		$scope.search.searchTypeId = LocationResource.getSelectedRegionId();
        	$scope.search.searchType = 'SEARCH_TYPE_AREA';
        	$scope.search.init = false;
    	}
    	
    	//$scope.pushBreadcrumbs(searchResultId, nextSearchType, searchResultName);
    	$scope.performSearch($scope.search);
    };
    
    $scope.isUndefinedOrNull = function(val) {
	    return angular.isUndefined(val) || val === null || val === "" 
	};

    /**
     * perform searching
     */
    $scope.performSearch = function(searchModel) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.searchResults = data.model;
    		$scope.totalSize = data.total;
    		$scope.numberOfPages();			
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        
        //FeeCollectionResource.queryAll({start: $scope.currentPage, max : $scope.pageSize, feeId: $scope.search.feeId, instituteId: $scope.search.instituteId},successCallback, errorCallback);
        FeeCollectionResource.hierarchySearch(searchModel, successCallback, errorCallback);
    };
    
    /**
     * clear searching
     */
    $scope.clearSearch = function() {
    	$scope.search.feeId = "";
    	$scope.search.instituteId = "";
    	$scope.search.gradeId="";
    	$scope.search.studentId="";
    	$scope.search.month="";
    	$scope.search.year=null;
    	LocationResource.unBindLocation();
    	$scope.instituteList = [];
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
    
    /**
     * populate dropdown field
     */
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
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList(locationHierarchy);
    	}
    });
	/*---------------------------------*/
    
    
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
    
    if (typeof $routeParams.search !== "undefined" && typeof $routeParams.searchId !== "undefined") {
    	$scope.goNextLevel($routeParams.searchId, $routeParams.search);
    }else{    
    	$scope.initSearch();
    }
    $scope.loadInitInstitute();
});