app.registerCtrl('NewExpenditureController', function ($scope, $location, locationParser, LocationResource, ExpenditureResource, UdvResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.expenditure = $scope.expenditure || {};
    $scope.headOfAccountList = [];
    $scope.expenditureTypeList = [];
    $scope.attachmentFile;
    
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		$.growl.notice({ message: "Added successfully!" });
    		$location.path("/Expenditure");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        
        $scope.expenditure.locationId = LocationResource.getSelectedLocationId();
        $scope.expenditure.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        ExpenditureResource.save($scope.expenditure, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Expenditure");
    };
    
    $scope.getHeadOfAccountList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.headOfAccountList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    $scope.getExpenditureTypeList = function(cateogryName, parentId) {
    	var successCallback = function(data, responseHeaders) {
    		$scope.expenditureTypeList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvListByParentId({categoryName:cateogryName, parentId:parentId},successCallback, errorCallback);
    };
    
    $scope.onHeadOfAccountChange = function(headsOfAccountId){
    	$scope.getExpenditureTypeList("Expenditure type", headsOfAccountId);
    };
    
    //file upload
    $scope.fileUpload = function(input){
    	if (input.files && input.files[0]) {
    		$scope.attachmentFile = input.files[0];
    		
            var fd = new FormData();
            fd.append('file', $scope.attachmentFile);
            fd.append('fileName', input.files[0].name);
            fd.append('type', input.files[0].type);
            fd.append('size', input.files[0].size);
    		 
            var successCallback = function(data, responseHeaders) {
        		$scope.expenditure.docName = data.model;
            };
            var errorCallback = function() {
            	console.log('error');
            }; 
            ExpenditureResource.uploadFile(fd, successCallback, errorCallback);
        }
    };
    
    $scope.getHeadOfAccountList('Heads of Accounts');
});