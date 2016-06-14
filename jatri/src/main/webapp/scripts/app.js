'use strict';

var app = angular.module ('emis',['ngRoute','ngResource','Validation', 'ngCookies']);

app.config(['$httpProvider','$routeProvider', '$controllerProvider', function($httpProvider, $routeProvider, $controllerProvider, $window) {
	
	$httpProvider.interceptors.push('responseObserver');
	$httpProvider.interceptors.push('redirectInterceptor');
	app.registerCtrl = $controllerProvider.register;
	
	$routeProvider
	  .when('/',{templateUrl:'views/landing.html',controller:'DashboardController', resolve: {factory: checkAuthorization('DASHBOARD', 'dashboard/dashboardController')}})
	  .when('/Login',{templateUrl:'views/login.html'})
	  .when('/403',{templateUrl:'accessDenied.html'})
	  
	  .when('/Rights',{templateUrl:'views/Right/search.html', controller:'SearchRightController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT', 'right/searchRightController')}})
	  .when('/Rights/new',{templateUrl:'views/Right/detail.html',controller:'NewRightController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT', 'right/newRightController')}})
	  .when('/Rights/edit/:RightId',{templateUrl:'views/Right/detail.html',controller:'EditRightController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','right/editRightController')}})
	  
	  .when('/Role',{templateUrl:'views/Role/search.html', controller:'SearchRoleController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_ROLE','role/searchRoleController')}})
	  .when('/Role/new',{templateUrl:'views/Role/detail.html',controller:'NewRoleController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_ROLE','role/newRoleController')}})
	  .when('/Role/edit/:RoleId',{templateUrl:'views/Role/detail.html',controller:'EditRoleController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_ROLE','role/editRoleController')}})
	  
	  .when('/Organization',{templateUrl:'views/Organization/search.html', controller:'SearchOrganizationController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_ORGANIZATION','organization/searchOrganizationController')}})
	  .when('/Organization/new',{templateUrl:'views/Organization/detail.html',controller:'NewOrganizationController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_ORGANIZATION','organization/newOrganizationController')}})
	  .when('/Organization/edit/:OrganizationId',{templateUrl:'views/Organization/detail.html',controller:'EditOrganizationController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_ORGANIZATION','organization/editOrganizationController')}})
	  
	  /*.when('/GradingSystem',{templateUrl:'views/GradingSystem/search.html', controller:'SearchGradingSystemController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','grading_system/searchGradingSystemController')}})
	  .when('/GradingSystem/new',{templateUrl:'views/GradingSystem/detail.html',controller:'NewGradingSystemController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','grading_system/newGradingSystemController')}})
	  .when('/GradingSystem/edit/:GradingSystemId',{templateUrl:'views/GradingSystem/detail.html',controller:'EditGradingSystemController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','grading_system/editGradingSystemController')}})
	  */
	  
	  .when('/Institute',{templateUrl:'views/Institute/search.html', controller:'SearchInstituteController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_INSTITUTE','institute/searchInstituteController')}})
	  .when('/Institute/new',{templateUrl:'views/Institute/detail.html',controller:'NewInstituteController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_INSTITUTE','institute/newInstituteController')}})
	  .when('/Institute/edit/:InstituteId',{templateUrl:'views/Institute/detail.html',controller:'EditInstituteController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_INSTITUTE','institute/editInstituteController')}})
	  
	  .when('/Teacher',{templateUrl:'views/Teacher/search.html', controller:'SearchTeacherController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_TEACHER','teacher/searchTeacherController')}})
	  .when('/Teacher/new',{templateUrl:'views/Teacher/detail.html',controller:'NewTeacherController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_TEACHER','teacher/newTeacherController')}})
	  .when('/Teacher/edit/:TeacherId',{templateUrl:'views/Teacher/detail.html',controller:'EditTeacherController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_TEACHER','teacher/editTeacherController')}})
	  	  
	  .when('/Udv',{templateUrl:'views/Udv/search.html', controller:'SearchUdvController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER_DEFINED','udv/searchUdvController')}})
	  .when('/Udv/new',{templateUrl:'views/Udv/detail.html',controller:'NewUdvController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER_DEFINED','udv/newUdvController')}})
	  .when('/Udv/edit/:UdvId',{templateUrl:'views/Udv/detail.html',controller:'EditUdvController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER_DEFINED','udv/editUdvController')}})
	  
	  .when('/Grade',{templateUrl:'views/Grade/search.html', controller:'SearchGradeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADE','grade/searchGradeController')}})
	  .when('/Grade/new',{templateUrl:'views/Grade/detail.html',controller:'NewGradeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADE','grade/newGradeController')}})
	  .when('/Grade/edit/:GradeId',{templateUrl:'views/Grade/detail.html',controller:'EditGradeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADE','grade/editGradeController')}})
	  
	  .when('/GradePoint',{templateUrl:'views/GradePoint/search.html', controller:'SearchGradePointController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADING_POINT','gradePoint/searchGradePointController')}})
	  .when('/GradePoint/new',{templateUrl:'views/GradePoint/detail.html',controller:'NewGradePointController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADING_POINT','gradePoint/newGradePointController')}})
	  .when('/GradePoint/edit/:GradePointId',{templateUrl:'views/GradePoint/detail.html',controller:'EditGradePointController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_GRADING_POINT','gradePoint/editGradePointController')}})

	  .when('/User',{templateUrl:'views/User/search.html', controller:'SearchUserController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER','user/searchUserController')}})
	  .when('/User/new',{templateUrl:'views/User/detail.html',controller:'NewUserController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER','user/newUserController')}})
	  .when('/User/edit/:UserId',{templateUrl:'views/User/detail.html',controller:'EditUserController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_USER','user/editUserController')}})
	  .when('/Settings',{templateUrl:'views/User/settings.html',controller:'UserSettingsController', resolve: {factory: checkAuthorization('DASHBOARD','user/userSettingsController')}})
	  
	  .when('/Donor',{templateUrl:'views/Donor/search.html', controller:'SearchDonorController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_DONOR','donor/searchDonorController')}})
	  .when('/Donor/new',{templateUrl:'views/Donor/detail.html',controller:'NewDonorController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_DONOR','donor/newDonorController')}})
	  .when('/Donor/edit/:DonorId',{templateUrl:'views/Donor/detail.html',controller:'EditDonorController', resolve: {factory: checkAuthorization('ORGANIZATION_MANAGEMENT_DONOR','donor/editDonorController')}})
	  
	  .when('/Fee',{templateUrl:'views/Fee/search.html', controller:'SearchFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_SETUP','fee/searchFeeController')}})
	  .when('/Fee/new',{templateUrl:'views/Fee/detail.html',controller:'NewFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_SETUP','fee/newFeeController')}})
	  .when('/Fee/engine',{templateUrl:'views/Fee/nDetail.html',controller:'FeeEngineController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_SETUP','fee/feeEngineController')}})
	  .when('/Fee/edit/:FeeId',{templateUrl:'views/Fee/detail.html',controller:'EditFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_SETUP','fee/editFeeController')}})
	  
	  .when('/TransactionHistory', { templateUrl: 'views/TransactionHistory/search.html', controller: 'SearchTransactionHistoryController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_TRANSACTION_HISTORY','transactionHistory/searchTransactionHistoryController') } })
	  .when('/TransactionHistory/new', { templateUrl: 'views/TransactionHistory/detail.html', controller: 'NewTransactionHistoryController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_TRANSACTION_HISTORY','transactionHistory/newTransactionHistoryController') } })
	  .when('/TransactionHistory/edit/:TransactionHistoryId', { templateUrl: 'views/TransactionHistory/detail.html', controller: 'EditTransactionHistoryController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_TRANSACTION_HISTORY','transactionHistory/editTransactionHistoryController') } })
	  .when('/TransactionHistory/detail/:TransactionId', { templateUrl: 'views/TransactionHistory/detail.html', controller: 'DetailTransactionHistoryController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_TRANSACTION_HISTORY','transactionHistory/detailTransactionHistoryController') } })
	  
	  
	  /*.when('/StudentFee', { templateUrl: 'views/StudentFee/search.html', controller: 'SearchStudentFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_COLLECTION','studentFee/searchStudentFeeController') } })
	  .when('/StudentFee/new', { templateUrl: 'views/StudentFee/detail.html', controller: 'NewStudentFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_COLLECTION','studentFee/newStudentFeeController') } })
	  .when('/StudentFee/edit/:StudentFeeId', { templateUrl: 'views/StudentFee/detail.html', controller: 'EditStudentFeeController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_COLLECTION','studentFee/editStudentFeeController') } })*/
	  
	  .when('/FeeCollection', { templateUrl: 'views/FeeCollection/search.html', controller: 'SearchFeeCollectionController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_COLLECTION','feeCollection/searchFeeCollectionController') } })
	  .when('/FeeCollection/collect', { templateUrl: 'views/FeeCollection/collect.html', controller: 'NewFeeCollectionController', resolve: {factory: checkAuthorization('FEES_MANAGEMENT_FEE_COLLECTION','feeCollection/newFeeCollectionController') } })
	  
	  .when('/Expenditure', { templateUrl: 'views/Expenditure/search.html', controller: 'SearchExpenditureController', resolve: {factory: checkAuthorization('FINANCIAL_MANAGEMENT_EXPENDITURE','expenditure/searchExpenditureController') } })
	  .when('/Expenditure/new',{templateUrl:'views/Expenditure/detail.html',controller:'NewExpenditureController', resolve: {factory: checkAuthorization('FINANCIAL_MANAGEMENT_EXPENDITURE','expenditure/newExpenditureController')}})
	  .when('/Expenditure/edit/:ExpenditureId',{templateUrl:'views/Expenditure/detail.html',controller:'EditExpenditureController', resolve: {factory: checkAuthorization('FINANCIAL_MANAGEMENT_EXPENDITURE','expenditure/editExpenditureController')}})

	  .when('/Income', { templateUrl: 'views/Income/search.html', controller: 'SearchIncomeController', resolve: {factory: checkAuthorization('INCOME','income/searchIncomeController') } })
	  .when('/Income/new',{templateUrl:'views/Income/detail.html',controller:'NewIncomeController', resolve: {factory: checkAuthorization('INCOME','income/newIncomeController')}})
	  .when('/Income/edit/:IncomeId',{templateUrl:'views/Income/detail.html',controller:'EditIncomeController', resolve: {factory: checkAuthorization('INCOME','income/editIncomeController')}})
	  .when('/IncomeReport',{templateUrl:'views/Income/report.html',controller:'IncomeReportController', resolve: {factory: checkAuthorization('INCOME','income/incomeReportController')}})

	  
	  .when('/Student', { templateUrl: 'views/Student/search.html', controller: 'SearchStudentController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_STUDENT','student/searchStudentController') } })
	  .when('/Student/new', { templateUrl: 'views/Student/detail.html', controller: 'NewStudentController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_STUDENT','student/newStudentController') } })
	  .when('/Student/edit/:StudentId', { templateUrl: 'views/Student/detail.html', controller: 'EditStudentController', resolve: {factory: checkAuthorization('INSTITUTE_MANAGEMENT_STUDENT','student/editStudentController') } })

	  .when('/EducationType', { templateUrl: 'views/EducationType/search.html', controller: 'SearchEducationTypeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE','educationType/searchEducationTypeController') } })
	  .when('/EducationType/new', { templateUrl: 'views/EducationType/detail.html', controller: 'NewEducationTypeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE','educationType/newEducationTypeController') } })
	  .when('/EducationType/edit/:EducationTypeId', { templateUrl: 'views/EducationType/detail.html', controller: 'EditEducationTypeController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE','educationType/editEducationTypeController') } })
	  
	  .when('/FinancialReport', { templateUrl: 'views/Report/financialReport.html', controller: 'FinancialReportController', resolve: {factory: checkAuthorization('EDUCATION_TYPE_MANAGEMENT_EDUCATION_TYPE','report/financialReportController') } })

	  /*.when('/StudentActivity', { templateUrl: 'views/StudentActivity/search.html', controller: 'SearchStudentActivityController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','studentActivity/searchStudentActivityController') } })
	  .when('/StudentActivity/new', { templateUrl: 'views/StudentActivity/detail.html', controller: 'NewStudentActivityController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','studentActivity/newStudentActivityController') } })
	  .when('/StudentActivity/edit/:StudentActivityId', { templateUrl: 'views/StudentActivity/detail.html', controller: 'EditStudentActivityController', resolve: {factory: checkAuthorization('USER_MANAGEMENT_RIGHT','studentActivity/editStudentActivityController') } })
*/
	  .otherwise({
		  redirectTo: '/'
	  });
}]).run(function($rootScope, UserResource, $window, $cookieStore) {
	$rootScope.getLoggedUser = function() {
    	var successCallback = function(data,responseHeaders) {
    		if(data.success === "1"){
    			// save in root scope
    			$rootScope.authenticated = true;
    			$rootScope.loogedUser = data.model;
    			
    			// save in session
    			$window.localStorage.setItem("authenticated", true);
    			$window.localStorage.setItem("loogedUser", angular.toJson(data.model));
    		}			
        };
        var errorCallback = function() {
        	console.log('error');
        };
        
        if(($window.localStorage == null) || ($window.localStorage != null && $window.localStorage.getItem("authenticated") !== "true")){
        	UserResource.loggedUser({}, successCallback, errorCallback);        	
        }else{
        	$rootScope.authenticated = $window.localStorage.getItem("authenticated");
			$rootScope.loogedUser = angular.fromJson($window.localStorage.getItem("loogedUser"));
        }
    };    
    $rootScope.getLoggedUser();
})
.controller('NavController', function NavController($scope, $rootScope, $location,$window, $cookieStore) {
	$scope.matchesRoute = function(route) {
	    var path = $location.path();
	    return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
    
    $rootScope.logOut = function(){
    	$window.localStorage.setItem("authenticated", false);
		$window.localStorage.setItem("loogedUser", null);
    }
    
    $rootScope.haveAccess = function(right){
    	if($rootScope.loogedUser){
    		if($rootScope.loogedUser.username === "admin"){
	    		return true;
	    	}else if($rootScope.loogedUser && $rootScope.loogedUser.roleModel){
	    		for(var i in $rootScope.loogedUser.roleModel.rightModelList){
	    			if((($rootScope.loogedUser.roleModel.rightModelList)[i].name) === right){
	    				return true;
	    			}
	    		}
	    	}
    	}
    	return false;
    };
}).factory('responseObserver', function responseObserver($q, $window) {
    return {
        'responseError': function(errorResponse) {
            switch (errorResponse.status) {
            case 403:
                $.growl.error({ message: "Your are not authorized!" });
                break;
            case 500:
            	$.growl.error({ message: "Your are not authorized!" });
                break;
            case 200:
            	break;
            case 302:
            	break;
            }
            return $q.reject(errorResponse);
        }
    };
}).factory('redirectInterceptor', function($q,$location,$window,$rootScope){
    return  {
        'response':function(response){        	
	        if (typeof response.data === 'string' && response.data.indexOf("/login")>-1) {
	        	$rootScope.logOut();
	        	$window.location.href = "/emis/login.html";
	            return $q.reject(response);
	        }else{
	        	return response;
	        }
        }
    }

});

var checkAuthorization = function(accessRightName, controllerSubLocation) 
{
    return ["$q", "$route", "$location", "$window", "$rootScope", "$cookieStore", function($q, $route, $location, $window, $rootScope, $cookieStore) {
        var deferred = $q.defer();
        
    	console.log("need to wait some time");
    	setTimeout(function() {
    		if(($window.localStorage !== null && $window.localStorage.getItem("authenticated") === "true" && $rootScope.haveAccess(accessRightName)) || accessRightName === "DASHBOARD" ){        
            	deferred.resolve(true);
            	$.ajaxSetup({ async:false, cache: true });        	
    			$.getScript("scripts/controllers/" + controllerSubLocation + ".js")
    			.done(function( script, textStatus ) {})
    			.fail(function( jqxhr, settings, exception ) {console.log( exception );});			
            }else{
            	deferred.resolve(false);
            	deferred.reject();
            	$location.path("/403");
            }
    	}, 500);
        
        return deferred.promise;
    }];
};

var getTemplateUrl = function(){
	return 'views/landing.html';
}


