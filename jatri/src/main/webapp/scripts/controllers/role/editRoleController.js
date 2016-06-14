app.registerCtrl('EditRoleController', function($scope, $routeParams, $location, RoleResource, RightResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.rightList = [];
    $scope.selectedRight = { 
    	right: []
	};
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.role = new RoleResource(self.original);
            $scope.selectedRight.right = angular.copy($scope.role.rightModelList);
        };
        var errorCallback = function() {
            $location.path("/Role");
        };
        RoleResource.get({RoleId:$routeParams.RoleId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.role);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Role");
        	//$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.role.rightModelList = $scope.selectedRight.right;
        $scope.role.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Role");
    };

    $scope.remove = function(role_name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + role_name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Role");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.role.$remove(successCallback, errorCallback);
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
    
    $scope.rightList = function() {
    	var successCallback = function(data,responseHeaders) {
    		$scope.rightList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        RightResource.queryAll({all: true},successCallback, errorCallback);
    };
    

	$scope.checkAll = function() {
		$scope.selectedRight.right = angular.copy($scope.rightList);
	};
	$scope.uncheckAll = function() {
		$scope.selectedRight.right = [];
	};
    
	$scope.get();
    $scope.rightList();
});