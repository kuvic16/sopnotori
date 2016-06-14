app.registerCtrl('EditTransactionHistoryController', function($scope, $routeParams, $location, TransactionHistoryResource, InstituteResource,GradeResource,FeeResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.feeLists=[];
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.transactionHistory = new TransactionHistoryResource(self.original);
            
            if (angular.isUndefined($scope.transactionHistory.instituteId) || $scope.transactionHistory.instituteId === null) {
        		$scope.transactionHistory.instituteId = "";
        	}
            
            if (angular.isUndefined($scope.transactionHistory.gradeId) || $scope.transactionHistory.gradeId === null) {
            	$scope.transactionHistory.gradeId = "";
        	}
        };
        var errorCallback = function() {
            $location.path("/TransactionHistory");
        };
        TransactionHistoryResource.get({TransactionHistoryId:$routeParams.TransactionHistoryId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.transactionHistory);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/TransactionHistory");
        	//$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        //console.log($scope.transactionHistory.mandatory);
        //$scope.transactionHistory.isMandatory = JSON.parse("true");;
        //console.log($scope.transactionHistory);
        $scope.transactionHistory.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/TransactionHistory");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/TransactionHistory");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.transactionHistory.$remove(successCallback, errorCallback);
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
    
    $scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
	};
	
	 $scope.getGradeList = function() {
			var successCallback = function(data, responseHeaders) {
				$scope.gradeList = data.model;
			};
			var errorCallback = function() {
				console.log('error');
			};
			GradeResource.queryAll({all : true}, successCallback, errorCallback);
		};
		
	$scope.getFeeList = function(){
		var successCallback = function(data, responseHeaders){
			$scope.feeLists = data.model;
		};
		var errorCallback = function(){
			console.log('error');
		};
		FeeResource.queryAll({all: true}, successCallback, errorCallback);
	}
    
    $scope.get();
    $scope.getFeeList();
    $scope.getInstituteList();
    $scope.getGradeList();
});