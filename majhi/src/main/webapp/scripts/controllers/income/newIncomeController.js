app.registerCtrl('NewIncomeController', function ($scope, $location, locationParser, 
		LocationResource, IncomeResource, UdvResource, rexValidation,$rootScope,
		InstituteResource) 
{
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.income = $scope.income || {};
    $scope.validation = rexValidation;
    $scope.instituteList = [];
    $scope.instituteDisable=true;
    $scope.udvList=[];
       
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		if(data.success === "1"){
	    		$.growl.notice({ message: "Added successfully!" });
	    		$location.path("/Income");
	            $scope.displayError = false;
    		}else{
    			$.growl.error({ message: data.message });
    		}
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        $scope.income.locationId = LocationResource.getSelectedLocationId();
        $scope.income.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        IncomeResource.save($scope.income, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Income");
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
});