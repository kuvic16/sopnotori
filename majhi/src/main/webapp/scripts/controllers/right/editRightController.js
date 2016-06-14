app.registerCtrl('EditRightController', function($scope, $routeParams, $location, RightResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.right = new RightResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Rights");
        };
        RightResource.get({RightId:$routeParams.RightId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.right);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Rights");
        	//$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.right.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Rights");
    };

    $scope.remove = function(right_name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + right_name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Rights");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.right.$remove(successCallback, errorCallback);
            },
            cancel: function(button) {
            	return false;
            },
            confirmButton: "Yes",
            cancelButton: "Nope",
            confirmButtonClass: "btn-danger",
            cancelButtonClass: "btn-info"
        });
//    	var msg = "Are you sure to delete " + right_name + "?"
//    	if(confirm(msg)){
//    		var successCallback = function(data) {
//    			if(data.success = '1'){
//    				$location.path("/Rights");
//    				$scope.displayError = false;
//    			}else{
//    				$scope.displayError = true;
//    			}
//            };
//            var errorCallback = function() {
//                $scope.displayError=true;
//            }; 
//            $scope.right.$remove(successCallback, errorCallback);
//        }
//        else{
//            return false;
//        }
    };
    
    $scope.get();
});