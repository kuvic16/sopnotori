app.registerCtrl('NewDonorController', function ($scope, $location, locationParser, DonorResource , UdvResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.donor = $scope.donor || {};
    $scope.udvList = [];

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Donor");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        DonorResource.save($scope.donor, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Donor");
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
    
    $scope.udvList('Donor type');
});