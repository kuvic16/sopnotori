app.registerCtrl('UserSettingsController', function($scope, $routeParams, $location, UserResource, UdvResource, $rootScope,rexValidation) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.udvList = [];
    $scope.user = $scope.user || {};
    $scope.validation = rexValidation;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.user = data.model;                        
            
        };
        var errorCallback = function() {
            $location.path("/User");
        };
        
        if($rootScope.loogedUser.username !== 'admin'){
        	UserResource.findSettingsById({UserId:$rootScope.loogedUser.id}, successCallback, errorCallback);
        }
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.user);
    };

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
        	if(data.success==1){
        		$.growl.notice({ message: "Updated successfully!" });
            	$scope.get();
    		}else{
    			$.growl.error({ message: "" + data.message });
    		}
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        UserResource.updateSettings($scope.user, successCallback, errorCallback);
        //$scope.user.$update(successCallback, errorCallback);
    };

    $scope.getUdvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.getUdvList('Designation, Staff grade');
    $scope.get();
});