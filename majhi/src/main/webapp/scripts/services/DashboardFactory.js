app.factory('DashboardResource', function($resource){
	var baseUrl = 'rest/dashboard';
	var resource = {};
	
	resource = $resource(baseUrl, {},
    		{
    			'stats':{method:'GET',isArray:false, url: baseUrl + '/stats'	}
    		}
    );
    return resource;
});
