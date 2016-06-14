app.registerCtrl('NewUdvController', function ($scope, $location, locationParser, UdvResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.udv = $scope.udv || {};
    $scope.categoryList = [];
    $scope.parentUdvList = [];
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Udv");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        UdvResource.save($scope.udv, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Udv");
    };
    
    $scope.categoryList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.categoryList = data.model;			
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        
        UdvResource.category({}, successCallback, errorCallback);
    };
    
    $scope.categoryChange = function(name) {
    	$scope.parentList(name);
    };
    
    $scope.parentList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.parentUdvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.categoryList();
});