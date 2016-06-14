app.registerCtrl('FinancialReportController', function($scope, $http, FinancialReportResource,
                                                       $routeParams, PagerService, LocationResource, UdvResource) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.searchResultsList = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.totalNumberOfColumn = 0;
    $scope.columnNameList = [];
    $scope.locationNames = [];
    $scope.pagerService = PagerService;
    $scope.maxPage = 0;

    $scope.reportPageLoad=false;

    $scope.headOfAccountSpanCount=0;
    
    $scope.colSpanArray = [];
    
    $scope.headOfAccountList = [];
    $scope.expenditureTypeList = [];
        
    //$scope.monthText=["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    
    $scope.numberOfPages = function() {
        pager = PagerService.getPager($scope.totalSize, $scope.pageSize, $scope.currentPage);
        $scope.pageRange = pager.pageRange;
        $scope.maxPage = pager.maxPage;
    };

    $scope.performSearch = function() {
        $('#dynamicTable').show();
    	var successCallbackForColumnHeaderNames = function(data,responseHeaders) {
    		$scope.colSpanArray = data.model[0];
    		$scope.columnNameList = data.model[1];
    		$scope.totalNumberOfColumn = data.total;

            //console.log("Page Loaded: " + $scope.reportPageLoad);
    		//console.log("Colspan Array: "+$scope.colSpanArray);
    		//console.log("Columnt  List: "+ $scope.columnNameList);
    		//console.log("Total Number Of Column: "+$scope.totalNumberOfColumn);

            var thColor=true;
            //if(!$scope.reportPageLoad) {

                $('#SpecialTable').show();
                //Execute Jquery code from angularJS
                var thHoaElement = jQuery('#hOa').css("background-color", "#8dcdef").
                attr('colspan', $scope.totalNumberOfColumn);
                thHoaElement.show();

                // Type of account header
                var trAccHeadElement = jQuery('#accHead')

                for (x in $scope.colSpanArray) {

                    if($scope.headOfAccountList[x].value=='Student fee Recovery') {
                        var thRow = $('<th></th>').addClass('text-center');
                        thRow.attr('colspan', 1);
                        trAccHeadElement.append(thRow);
                    }


                    //$scope.headOfAccountList[x].spanColumn =  $scope.colspanArray[x];
                    //console.log("Assign Operation : "+$scope.headOfAccountList[x].spanColumn);


                    var thRow = $('<th></th>').addClass('text-center');
                    thRow.attr('colspan', $scope.colSpanArray[x]);
                    thRow.text($scope.headOfAccountList[x].value);

                    //toggle background color of table header============Start
                    if(thColor){
                        thRow.css("background-color", "#c7e0c3");
                        thColor=false;
                    }
                    else {
                        thRow.css("background-color", "#a9e5a0");
                        thColor=true;
                    }
                    //toggle background color of table header============End

                    trAccHeadElement.append(thRow);

                }
                trAccHeadElement.show();
           // }
            
            $scope.reportPageLoad= true;



	    	var successCallbackForResult = function(data,responseHeaders){
                $scope.searchResultsList = data.model[0];
                $scope.footerList =  data.model[1];

	    		var col;
	    		for(x in $scope.searchResultsList)	{
	    			console.log("Count: " + x);
	    			  col = $scope.searchResultsList[x];
	    			  for(y in col){
	    				  console.log("Data :" + col[y]);
	    			  }
	    				  
	    		}
	    		
	    		$scope.totalSize = data.total;
	    		$scope.numberOfPages();	
	    	}
	    	
	    	 var errorCallbackForResult = function() {
	         	console.log('error');
	         };

            $scope.search.locationId = LocationResource.getSelectedLocationId();
            $scope.search.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
	        FinancialReportResource.getColumnValues
            (
    											{
    												start: 					$scope.currentPage, 
    												max : 					$scope.pageSize, 
    												expenditureTypeUdvId: 	$scope.search.expenditureTypeUdvId,        								
    												headsOfAccountUdvId:	$scope.search.headsOfAccountUdvId,
                                                    locationId:             $scope.search.locationId,
    												locationHierarchy: 		$scope.search.locationHierarchy, 
    												year: 					$scope.search.year, 
    												month: 					$scope.search.month,
    												totalNumberOfColumn: 	$scope.totalNumberOfColumn
    											},
    											successCallbackForResult, errorCallbackForResult
            );


        };
        var errorCallbackForHeaderColumnNames = function() {
        	console.log('error');
        };
        // get column names for each head of account
        FinancialReportResource.getColumnsNameByHeadOfAccount
        (
            {
                headsOfAccountUdvId:	$scope.search.headsOfAccountUdvId,
                year: 					$scope.search.year
            },
            successCallbackForColumnHeaderNames,errorCallbackForHeaderColumnNames
        );

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
                FinancialReportResource.remove({ExpenditureId: id}, successCallback, errorCallback);
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

    //it call for populate drop down list
    $scope.getHeadOfAccountList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.headOfAccountList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };

    //it call for populate drop down list
    $scope.getUdvListByParentId = function(parentId) {
    	var successCallback = function(data, responseHeaders) {
    		$scope.expenditureTypeList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvListByParentId({parentId:parentId},successCallback, errorCallback);
        $("#dynamicTable > thead th").not($("#hOa")).remove();
        $("#dynamicTable > thead th").not($("#accHead")).remove();
        $("#dynamicTable > tbody tr").remove();
    };


    $scope.getHeadOfAccountList('Heads of Accounts'); // udv call

    function dynmicTable() {
        var table = $("<table/>").attr('border',1)
        var tableHead = $("<thead/>")
        
    }
});