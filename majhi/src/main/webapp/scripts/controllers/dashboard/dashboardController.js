app.registerCtrl('DashboardController', function($scope, $location, locationParser, DashboardResource, FeeCollectionResource,$routeParams, LocationResource, InstituteResource, GradeResource, StudentResource, ExpenditureResource) {
	$scope.disabled = false;
	$scope.$location = $location;
	$scope.stats = $scope.stats || {};
	$scope.activities = [];
	
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

	$scope.getStats = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.stats = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		DashboardResource.stats({}, successCallback, errorCallback);
	};
	$scope.getStats();

	
	$scope.getActivites = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.activities = data.model;    		
        };
        var errorCallback = function() {
        	console.log('error');
        };        
        ExpenditureResource.queryAll({start: 0, max : 10}, successCallback, errorCallback);
    };
    $scope.getActivites();
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Bar chart
	 */
	$scope.randomColorFactor = function() {
		return Math.round(Math.random() * 255);
	};
	$scope.randomColor = function() {
		return 'rgba(' + $scope.randomColorFactor() + ',' + $scope.randomColorFactor() + ',' + $scope.randomColorFactor() + ',.7)';
	};
	
	$scope.barChartData = {
			labels : [],
			datasets : [ {
					label : 'Total amount',
					backgroundColor : '#337ab7',
					data : [  ]
				},
				{
					label : 'Total collected',
					backgroundColor : '#5cb85c',
					data : [  ]
				},
				{
					label : 'Total due',
					backgroundColor : '#d9534f',
					data : [  ]
				} 
			]	
	};

	$scope.ctx = document.getElementById("canvas").getContext("2d");
	$scope.myBar = new Chart($scope.ctx, {
		type : 'bar',
		data : $scope.barChartData,
		options : {
			elements : {
				rectangle : {
					borderWidth : .5,
					borderSkipped : 'bottom'
				}
			},
			responsive : true,
			legend : {
				position : 'top',
			},
			title : {
				display : false,
				text : 'Fee Chart Report'
			}
		}
	});
	
	
	
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
    	//console.log("---");
    	//console.log(LocationResource.getSelectedLocationType());
    	//console.log($scope.search);
    	
    	$scope.currentPage = 0;
        $scope.pageSize= 10;
    	
        $scope.search.init = false;
    	$scope.performSearch($scope.search);
    };

    /**
     * perform searching
     */
    $scope.performSearch = function(searchModel) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.searchResults = data.model;
    		
    		for(var i=0; i<$scope.searchResults.feeCollectionModels.length; i++){
    			//console.log($scope.searchResults.feeCollectionModels[i]);
    			if ($scope.barChartData.datasets.length > 0) {
    				$scope.barChartData.labels.push($scope.searchResults.feeCollectionModels[i].searchResultName);
                	$scope.barChartData.datasets[0].data.push($scope.searchResults.feeCollectionModels[i].totalPattern3);                        
                	$scope.barChartData.datasets[1].data.push($scope.searchResults.feeCollectionModels[i].totalPattern4);
                	$scope.barChartData.datasets[2].data.push($scope.searchResults.feeCollectionModels[i].totalPattern5);
                    $scope.myBar.update();
                }
    		}
        };
        var errorCallback = function() {
        	console.log('error');
        };         
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
	
	// Load institute by location hierarchy =============================================================================
    $scope.getInstituteList = function(locationHierarchy) {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
			$scope.instituteDisable = false;
		};
		var errorCallback = function() {
			console.log('error');
		};		
		InstituteResource.queryAll({locationHierarchy : locationHierarchy}, successCallback, errorCallback);
    };
    //===================================================================================================================
    
    
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

});