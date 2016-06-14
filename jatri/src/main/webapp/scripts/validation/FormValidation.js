
var validation = angular.module('Validation',[]);

validation.factory('rexValidation', function() {
	
	var rexPattern = {"number" 		: "/^\\d*(?:\\.\\d{0,20})?$/",
					  "email" 		: "/^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$/",
					  "userName" : "/^(?:[0-9a-z_]+$)/",
					  "phoneNumber" : "/^(?:[0-9 +,-]+$)/",
					  "MaxValidation" : "^[1-9][0-9]?$|^100$"};
	
	return  rexPattern;
});