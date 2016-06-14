app.registerCtrl('EditIncomeController', function($scope, $routeParams, $location, locationParser, 
		LocationResource, IncomeResource, UdvResource,rexValidation,$rootScope,
		InstituteResource)
		
{
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.income =  {};
    $scope.validation = rexValidation;
    $scope.udvList=[];

   
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.income = new IncomeResource(self.original);
            
            if (angular.isUndefined($scope.income.indicatorUdvId) || $scope.income.indicatorUdvId === null) {
        		$scope.income.indicatorUdvId = "";
        	}
            
            // Set location
            if (!(angular.isUndefined($scope.income.locationId) || $scope.income.locationId === null)) {
            	LocationResource.setLocation($scope.income.locationId);
            }           
            
        };
        var errorCallback = function() {
            $location.path("/Income");
        };
        IncomeResource.get({IncomeId:$routeParams.IncomeId}, successCallback, errorCallback);
    };

    
    /**
     * clean the income varibale
     */
    $scope.isClean = function() {
    	$scope.income.locationId = LocationResource.getSelectedLocationId();
        $scope.income.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        return angular.equals(self.original, $scope.income);
    };

    /**
     * update income details
     */
    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
        	if(data.success === "1"){
	        	$.growl.notice({ message: "Updated successfully!" });
	        	$location.path("/Income");
	            $scope.displayError = false;
        	}else{
    			$.growl.error({ message: data.message });
    			$scope.get();
    		}
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        $scope.income.$update(successCallback, errorCallback);
    };

    
    /**
     * cancel editing income
     */
    $scope.cancel = function() {
    	$location.path("/Income");
    };

    
    /**
     * remove income 
     */
    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Income");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.income.$remove(successCallback, errorCallback);
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
    
    
    $scope.udvList = function(cateogryNames) {
        var successCallback = function(data,responseHeaders) {
            $scope.udvList = data.model;
        };
        var errorCallback = function() {
            console.log('error');
        };
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };

    $scope.udvList('Income indicator');
    $scope.get();
});