app.registerCtrl('EditExpenditureController', function($scope, $routeParams, $location, locationParser, LocationResource, ExpenditureResource, UdvResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.expenditure = $scope.expenditure || {};
    $scope.headOfAccountList = [];
    $scope.expenditureTypeList = [];
    $scope.attachmentFile;
    
    
    /**
     * get expenditure details by expenditure id
     */
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data.model;
            $scope.expenditure = new ExpenditureResource(self.original);
            
            if (angular.isUndefined($scope.expenditure.expenditureTypeUdvId) || $scope.expenditure.expenditureTypeUdvId === null) {
        		$scope.expenditure.expenditureTypeUdvId = "";
        	}
            
            if (angular.isUndefined($scope.expenditure.headsOfAccountUdvId) || $scope.expenditure.headsOfAccountUdvId === null) {
            	$scope.expenditure.headsOfAccountUdvId = "";
        	}else{
        		$scope.onHeadOfAccountChange($scope.expenditure.headsOfAccountUdvId);
        	}
            
            if (!(angular.isUndefined($scope.expenditure.locationId) || $scope.expenditure.locationId === null)) {
            	LocationResource.setLocation($scope.expenditure.locationId);
            }
        };
        var errorCallback = function() {
            $location.path("/Expenditure");
        };
        ExpenditureResource.get({ExpenditureId:$routeParams.ExpenditureId}, successCallback, errorCallback);
    };

    
    /**
     * clean the expenditure varibale
     */
    $scope.isClean = function() {
    	$scope.expenditure.locationId = LocationResource.getSelectedLocationId();
        $scope.expenditure.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
        return angular.equals(self.original, $scope.expenditure);
    };

    /**
     * update expenditure details
     */
    $scope.save = function() {
        var successCallback = function(){
        	$.growl.notice({ message: "Updated successfully!" });
        	$location.path("/Expenditure");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        
        $scope.expenditure.$update(successCallback, errorCallback);
    };

    
    /**
     * cancel editing expenditure
     */
    $scope.cancel = function() {
    	$location.path("/Expenditure");
    };

    
    /**
     * remove expenditure 
     */
    $scope.remove = function(name) {
    	$.confirm({
    		text: "Are you sure to delete <b>" + name + "</b>?",
            confirm: function(button) {
            	var successCallback = function(data) {
            		if(data.success = '1'){
            			$.growl.notice({ message: "Deleted successfully!" });
        				$location.path("/Expenditure");
        				$scope.displayError = false;
        			}else{
        				$scope.displayError = true;
        			}
                };
                var errorCallback = function() {
                    $scope.displayError=true;
                }; 
                $scope.expenditure.$remove(successCallback, errorCallback);
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
    
    
    /**
     * heads of account list
     */
    $scope.getHeadOfAccountList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.headOfAccountList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    /**
     * expenditure type list
     */
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
    
    /**
     * file upload
     */
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
    $scope.get();
});