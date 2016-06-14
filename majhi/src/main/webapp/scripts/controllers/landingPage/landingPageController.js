app.registerCtrl('LandingPageController', function($scope, $rootScope, UserResource) {
	
//	$rootScope.getLoggedUser = function() {
//    	var successCallback = function(data,responseHeaders) {
//    		if(data.success === "1"){
//    			$rootScope.authenticated = true;
//    			$rootScope.loogedUser = data.model;
//    		}			
//        };
//        var errorCallback = function() {
//        	console.log('error');
//        };
//        console.log("asdsad");
//        if(!$rootScope.authenticated){
//        	UserResource.loggedUser({}, successCallback, errorCallback);
//        }
//    };
//    
//    if(!$rootScope.authenticated){
//    	$rootScope.getLoggedUser();    
//    }
});