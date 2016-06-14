app.registerCtrl('EditOrganizationController', function($scope, $routeParams, $location, OrganizationResource, rexValidation ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.validation = rexValidation;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.organization = new OrganizationResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Organization");
        };
        OrganizationResource.get({OrganizationId:$routeParams.OrganizationId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.organization);
    };

    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Organization");
            //$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.organization.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
    	$location.path("/Organization");
    };

    $scope.remove = function(organization_name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + organization_name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Organization");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.organization.$remove(successCallback, errorCallback);
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
    
    $scope.get();
});