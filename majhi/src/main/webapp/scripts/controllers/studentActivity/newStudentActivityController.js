app.registerCtrl('NewStudentActivityController', function ($scope, $location, locationParser, StudentActivityResource, UdvResource, InstituteResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.studentActivity = $scope.studentActivity || {};
    $scope.instituteList = [];

    $scope.save = function() 
    {
    	var successCallback = function(data,responseHeaders)
    	{
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            $location.path("/StudentActivity");
            $scope.displayError = false;
        };
        
        var errorCallback = function() 
        {
            $scope.displayError = true;
        };
        
        StudentActivityResource.save($scope.studentActivity, successCallback, errorCallback);
    };
    
    $scope.cancel = function() 
    {
        $location.path("/StudentActivity");
    };
    
    $scope.udvList = function(cateogryNames) 
    {
    	var successCallback = function(data,responseHeaders) 
    	{
    		$scope.udvList = data.model;
        };
        var errorCallback = function() 
        {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.getInstituteList = function() {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
		};
		var errorCallback = function() {
			console.log('error');
		};
		InstituteResource.queryAll({all : true}, successCallback, errorCallback);
	};
	$scope.getInstituteList();
    $scope.udvList('Student activity type');
});