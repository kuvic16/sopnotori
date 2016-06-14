app.registerCtrl('EditUdvController', function($scope, $routeParams, $location, UdvResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.categoryList = [];
    $scope.parentUdvList = [];
    
    $scope.get = function() {
        var successCallback = function(data){
    		self.original = data.model;
            $scope.udv = new UdvResource(self.original);
            $scope.parentList($scope.udv.parentCategory);
        };
        var errorCallback = function() {
            $location.path("/Udv");
        };
        UdvResource.get({UdvId:$routeParams.UdvId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.udv);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Udv");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.udv.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Udv");
    };

    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Udv");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.udv.$remove(successCallback, errorCallback);
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
    
    $scope.get();
    $scope.categoryList();    
});