app.registerCtrl('NewStudentFeeController', function ($scope, $location, locationParser, StudentFeeResource, InstituteResource,GradeResource,FeeResource, StudentResource, LocationResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.studentFee = $scope.studentFee || {};
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.studentFeeList=[];
    $scope.studentList=[];
    $scope.transactionHistoryList=[];
    $scope.totalAmount=0.0;
    $scope.totalPaid=0.0;
    $scope.totalDue=0.0;
    $scope.monthText=["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var date = new Date();
    $scope.studentFee.year = date.getFullYear();
    $scope.studentFee.month = date.getMonth();
    $scope.tableDataLoaded = false;
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		$.growl.notice({ message: "Added successfully!" });
    		$location.path("/StudentFee");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        //console.log($scope.studentFeeList);
        StudentFeeResource.updateAll($scope.studentFeeList, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/StudentFee");
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
	
	$scope.getStudentFeeList = function(studentRoll, studentId, instituteId, gradeId, year, month){
		
		var successCallback = function(data, responseHeaders){
			$scope.studentFeeList = data.model;
			$scope.tableDataLoaded=true;
			for (th in $scope.studentFeeList) {
				$scope.studentFeeList[th].amount = parseInt($scope.studentFeeList[th].amount);
				$scope.studentFeeList[th].deposited = parseInt($scope.studentFeeList[th].deposited);
				$scope.studentFeeList[th].due = parseInt($scope.studentFeeList[th].due);
				$scope.studentFeeList[th].pay = parseInt($scope.studentFeeList[th].pay);
				
//				$scope.totalAmount += parseInt($scope.studentFeeList[th].amount);
//				$scope.totalPaid += parseInt($scope.studentFeeList[th].deposited);
//				$scope.totalDue += parseInt($scope.studentFeeList[th].due);
				
				if(!$scope.studentFeeList[th].deposited || $scope.studentFeeList[th].deposited == 0){
					$scope.studentFeeList[th].status = "has-error";
			    }else{
			    	if($scope.studentFeeList[th].deposited == $scope.studentFeeList[th].amount){
			    		$scope.studentFeeList[th].status = "has-success";
			    	}else{
			    		$scope.studentFeeList[th].status = "has-warning";
			    	}
			    }
			}
		};
		
		var errorCallback = function(){
			console.log('error');
		};
		
		StudentFeeResource.listByStudent({studentRoll: studentRoll, studentId: studentId, instituteId: instituteId, gradeId: gradeId, year: year, month: month}, successCallback, errorCallback);
		$scope.getTransactionHistoryList(studentId,year);
		
	};
	
	
	$scope.getTransactionHistoryList = function(studentId, year){
		
		var successCallback = function(data,responseHeaders){
			$scope.transactionHistoryList = data.model;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
		
		StudentFeeResource.listAllByStudent({studentId: studentId, year: year},successCallback, errorCallback);
	};
	
	$scope.getTotaAmountInTransactionHistory = function(){
		var sum = 0;
		for(th in $scope.transactionHistoryList)
			{
				if($scope.transactionHistoryList[th].amount){
					sum +=parseInt($scope.transactionHistoryList[th].amount);
				}
			}
		return sum;
	};
	
	
	$scope.getTotaDepositedAmountInTransactionHistory = function(){
		var sum = 0;
		for(th in $scope.transactionHistoryList)
			{
				if($scope.transactionHistoryList[th].deposited){
					sum +=parseInt($scope.transactionHistoryList[th].deposited);
				}
			}
		return sum;
	};
	
	$scope.getTotalAmount = function(){
		var sum = 0;
		for (th in $scope.studentFeeList) {
			if($scope.studentFeeList[th].amount){
				sum += parseInt($scope.studentFeeList[th].amount);
			}
		}
		return sum;
	}
	
	$scope.getTotalPaid = function(){
		var sum = 0;
		for (th in $scope.studentFeeList) {
			if($scope.studentFeeList[th].deposited){
				sum += parseInt($scope.studentFeeList[th].deposited);
			}
		}
		return sum;
	}
	
	$scope.getTotalDue = function(){
		var sum = 0;
		for (th in $scope.studentFeeList) {
			if($scope.studentFeeList[th].due){
				sum += parseInt($scope.studentFeeList[th].due);
			}
		}
		return sum;
	}
	
	$scope.getToBePaid = function(){
		var sum = 0;
		for (th in $scope.studentFeeList) {
			if($scope.studentFeeList[th].pay && ($scope.studentFeeList[th].status == "has-warning" || $scope.studentFeeList[th].status == "has-error")){
				sum += parseInt($scope.studentFeeList[th].pay);
			}
		}
		return sum;
	}
	
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
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
//    	$scope.fee.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList(locationHierarchy);
    	}
    });
   //===============================================================================================
		
	//$scope.getInstituteList();
});