app.registerCtrl('EditDonorController', function($scope, $routeParams, $location, DonorResource , UdvResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.udvList = [];
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.donor = new DonorResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Donor");
        };
        DonorResource.get({DonorId:$routeParams.DonorId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.donor);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Donor");
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.donor.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Donor");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Donor");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.donor.$remove(successCallback, errorCallback);
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
    
    $scope.udvList = function(cateogryNames, callback) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
    		callback();
        };
        var errorCallback = function() {
        	console.log('error');
        	callback();
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    
    $scope.udvList('Donor type', function(){
    	$scope.get();
    }); 
    
    //$scope.get();
});