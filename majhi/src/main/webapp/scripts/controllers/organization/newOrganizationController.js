app.registerCtrl('NewOrganizationController', function ($scope, $location, locationParser, OrganizationResource, rexValidation ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.organization = $scope.organization || {};
    
    $scope.validation = rexValidation;
    

    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/Organization");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        OrganizationResource.save($scope.organization, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Organization");
    };
});