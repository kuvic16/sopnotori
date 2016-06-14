app.registerCtrl('NewTransactionHistoryController', function ($scope, $location, locationParser, TransactionHistoryResource, InstituteResource,GradeResource,FeeResource, StudentResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.transactionHistory = $scope.transactionHistory || {};
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.transactionHistoryList=[];
    $scope.studentList=[];
    $scope.totalAmount=0.0;
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		$.growl.notice({ message: "Added successfully!" });
    		$location.path("/TransactionHistory");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        //console.log($scope.transactionHistoryList);
        TransactionHistoryResource.updateAll($scope.transactionHistoryList, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/TransactionHistory");
    };
    
    $scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
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
	
	$scope.getTransactionHistoryList = function(studentId, instituteId, gradeId, year){
		var successCallback = function(data, responseHeaders){
			$scope.transactionHistoryList = data.model;
			for (th in $scope.transactionHistoryList) {
				$scope.transactionHistoryList[th].amount = parseInt($scope.transactionHistoryList[th].amount);
				$scope.transactionHistoryList[th].deposited = parseInt($scope.transactionHistoryList[th].deposited);
				
				$scope.totalAmount += parseInt($scope.transactionHistoryList[th].amount);
				if(!$scope.transactionHistoryList[th].deposited || $scope.transactionHistoryList[th].deposited == 0){
					$scope.transactionHistoryList[th].deposited = $scope.transactionHistoryList[th].amount;
					$scope.transactionHistoryList[th].status = "has-error";
			    }else{
			    	if($scope.transactionHistoryList[th].deposited == $scope.transactionHistoryList[th].amount){
			    		$scope.transactionHistoryList[th].status = "has-success";
			    	}else{
			    		$scope.transactionHistoryList[th].status = "has-warning";
			    	}
			    }
			}
		};
		var errorCallback = function(){
			console.log('error');
		};
		TransactionHistoryResource.listByStudent({studentId: studentId, instituteId: instituteId, gradeId: gradeId, year: year}, successCallback, errorCallback);
	}
	
	$scope.getToBePaid = function(){
		var sum = 0;
		for (th in $scope.transactionHistoryList) {
			if($scope.transactionHistoryList[th].deposited && ($scope.transactionHistoryList[th].status == "has-warning" || $scope.transactionHistoryList[th].status == "has-error")){
				sum += parseInt($scope.transactionHistoryList[th].deposited);
			}
		}
		return sum;
	}
	
	$scope.getInstituteList();
});