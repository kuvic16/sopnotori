app.registerCtrl('NewStudentController', function 
												(
													$scope, 
													$location, 
													locationParser, 
													StudentResource, 
													UdvResource, 
													InstituteResource, 
													GradeResource,
													rexValidation,
													LocationResource,
													$rootScope
												) 
{
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.student = $scope.student || {};
    $scope.instituteList = [];
    $scope.gradeList=[];
    $scope.validation = rexValidation;
    
    $scope.instituteDisable=true;
       
    $scope.save = function() {
    	var successCallback = function(data,responseHeaders){
    		//var id = data.model.id;
    		$.growl.notice({ message: "Added successfully!" });
            // $location.path("/Student");
    		$scope.resetFieldsValue();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        StudentResource.save($scope.student, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Student");
    };
    
    $scope.udvList = function(cateogryNames) {
    	var successCallback = function(data,responseHeaders) {
    		$scope.udvList = data.model;
        };
        var errorCallback = function() {
        	console.log('error');
        }; 
        UdvResource.udvList({CategoryNames:cateogryNames},successCallback, errorCallback);
    };
    
    // Load institute by location hierarchy =============================================================================
    $scope.getInstituteList = function(locationHierarchy) {
		var successCallback = function(data, responseHeaders) {
			$scope.instituteList = data.model;
			$scope.instituteDisable = false;
		};
		var errorCallback = function() {
			console.log('error');
		};		
		//InstituteResource.queryAll({locationHierarchy : locationHierarchy}, successCallback, errorCallback);
		InstituteResource.hierarchy({id : locationHierarchy}, successCallback, errorCallback);
    };
    //===================================================================================================================
    
    // watch for ng-model value change for this case 'watchForLocationChange' ====================
    $scope.$watch('watchForLocationChange', function (newValue) {
    	$scope.instituteDisable = true;
    	$scope.instituteList = [];
    	$scope.student.instituteId = '';
    	if(LocationResource.getSelectedLocationHierarchyId() !== null && LocationResource.getSelectedLocationHierarchyId() !== ''){
    		$scope.student.locationHierarchy = LocationResource.getSelectedLocationHierarchyId();
    		$scope.getInstituteList($scope.student.locationHierarchy);
    	}
    });
  //===============================================================================================
    
  	
	 $scope.getGradeList = function(instituteId) {
			var successCallback = function(data, responseHeaders) {
				$scope.gradeList = data.model;
			};
			var errorCallback = function() {
				console.log('error');
			};
			GradeResource.listByInstituteId({InstituteId: instituteId}, successCallback, errorCallback);
	};
	
	
	$scope.resetFieldsValue = function() {
		$scope.student.id=null;
		//$scope.student.instituteId='';
		//$scope.student.gradeId='';
		$scope.student.studentId=null;
		$scope.student.sessionStart=null;
		$scope.student.sessionEnd=null;
		$scope.student.sex=null;
		$scope.student.studentFirstName=null;
		$scope.student.studentMiddleName=null;
		$scope.student.studentLastName=null;
		$scope.student.studentTypeUdvId=null;
		$scope.student.typeOfCsnUdvId=null;
		$scope.student.typeOfEthnicityCommunityUdvId=null;
		$scope.student.residentialAddress=null;
		$scope.student.dateOfBirth=null;
		$scope.student.religion=null;
		$scope.student.height=null;
		$scope.student.weight=null;
		$scope.student.dialectSpoken=null;
		$scope.student.fatherName=null;
		$scope.student.fatherDob=null;
		$scope.student.fatherEducationalAttainment=null;
		$scope.student.fatherOccupation=null;
		$scope.student.motherName=null;
		$scope.student.motherDob=null;
		$scope.student.motherEducationalAttainment=null;
		$scope.student.motherOccupation=null;
		$scope.student.familyMembersInvolveWithBrac=null;
		$scope.student.familyMembersInvolveWithBracService=null;
		$scope.student.transferedSchool=null;
		$scope.student.transferredSchoolId=null;
		$scope.student.nameOfTransferredSchool=null;
		$scope.student.address=null;
		$scope.student.involvedWithChhatrabandhu=null;
		$scope.student.bracGraduate=null;
		$scope.student.dropout=null;
		$scope.student.guardianMobile=null;
		$scope.student.waiver=null;
		
		//$scope.student.locationTypeUdvId=null;
		//$scope.student.locationId=null;
		//$scope.student.locationHierarchy=null;
		$scope.student.latitude=null;
		$scope.student.longitude=null;
	};
	
	$scope.loadInitInstitute = function(){
		var userHierarchy = $rootScope.loogedUser.locationHierarchy;
		if($rootScope.loogedUser.username !== 'admin'){
	    	var userHierarchy = $rootScope.loogedUser.locationHierarchy;
	    	var hierarchy = userHierarchy.split(">");
			if(hierarchy.length == 5){
				$scope.getInstituteList(userHierarchy);
			}
	    }
	};
    
    $scope.udvList('Student type, Csn type, Ethnicity community type, Dialect spoken, Religion');
    $scope.loadInitInstitute();
    //$scope.getInstituteList();
    //$scope.getGradeList();
    
});