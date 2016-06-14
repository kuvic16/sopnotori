app.factory('LocationResource', function($resource){
	var baseUrl = 'http://brachub.brac.net:8080/brachub/bips/location';
	//var baseUrl = 'http://172.26.4.65/brachub/bips/location';	
	var resource = {};
    resource = $resource(	
    						baseUrl,
    					 	{},
				    		{
				    			'getAllDivision':{method:'GET', url: baseUrl + '/getAllDivision'},
				    			'getAllDistrict':{method:'GET', url: baseUrl + '/getAllDistrict'},
				    			'getAllUpazila':{method:'GET', url: baseUrl + '/getAllUpazila'},
				    			'getAllDistrictByDivisionId':{method:'GET', url: baseUrl + '/getAllDistrictByDivisionId/:id'},
				    			'getAllUpazilaByDistrictId':{method:'GET', url: baseUrl + '/getAllUpazilaByDistrictId/:id'},
				    			'getLocationById':{method:'GET', url: baseUrl + '/getLocationById/:id'},
				    			
				    			'getAllRegion':{method:'GET', url: baseUrl + '/getLocationsByCategory/REGION'},
				    			'getAllArea':{method:'GET', url: baseUrl + '/getLocationsByCategory/AREA', isArray:false, params: {parentId:'@parentId'}},
				    			'getAllBranch':{method:'GET', url: baseUrl + '/getLocationsByCategory/BRANCH', isArray:false, params: {parentId:'@parentId'}}
				    		}
    					);
    return resource;
});