app.registerCtrl('EditStudentActivityController', function($scope, $routeParams, $location, StudentActivityResource, UdvResource,InstituteResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.instituteList = [];
    
    $scope.get = function() 
    {
        var successCallback = function(data)
        {
            self.original = data.model;
            $scope.studentActivity = new StudentActivityResource(self.original);
            
            if (angular.isUndefined($scope.studentActivity.instituteId) || $scope.studentActivity.instituteId === null) {
        		$scope.studentActivity.instituteId = "";
        	}
        };
        var errorCallback = function() 
        {
            $location.path("/StudentActivity");
        };
        
        StudentActivityResource.get({StudentActivityId:$routeParams.StudentActivityId}, successCallback, errorCallback);
    };

    $scope.isClean = function() 
    {
        return angular.equals(self.original, $scope.studentActivity);
    };

    $scope.save = function() 
    {
        var successCallback = function()
        {
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/StudentActivity");
            //$scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() 
        {
            $scope.displayError=true;
        };
        
        $scope.studentActivity.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() 
    {
    	$location.path("/StudentActivity");
    };

    $scope.remove = function(name) 
    {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) 
            {
            	var successCallback = function(data) 
            	{
            		if(data.success = '1')
            		{
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/StudentActivity");
        				$scope.displayError = false;
        			}else
        			{
        				$scope.displayError = true;
        			}
                };
                
                var errorCallback = function() 
                {
                    $scope.displayError=true;
                }; 
                $scope.studentActivity.$remove(successCallback, errorCallback);
            },
            cancel: function(button) 
            {
            	return false;
            },
            confirmButton: "Yes",
            cancelButton: "Nope",
            confirmButtonClass: "btn-danger",
            cancelButtonClass: "btn-info"
        });
    };
    
    $scope.udvList = function(cateogryNames, callback) 
    {
    	var successCallback = function(data,responseHeaders) 
    	{
    		$scope.udvList = data.model;
    		callback();
        };
        var errorCallback = function() 
        {
        	console.log('error');
        	callback();
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
    
    
    $scope.udvList('Student activity type');  
    $scope.getInstituteList();
    $scope.get();
});