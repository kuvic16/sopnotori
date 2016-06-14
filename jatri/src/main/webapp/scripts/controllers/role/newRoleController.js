app.registerCtrl('NewRoleController', function ($scope, $location, locationParser, RoleResource, RightResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.role = $scope.role || {};
    $scope.rightList = [];
    $scope.selectedRight = { 
    	right: []
	};

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Role");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        //console.log($scope.selectedRight.right);
        $scope.role.rightModelList = $scope.selectedRight.right;
        RoleResource.save($scope.role, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Role");
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
    
    $scope.rightList();
});