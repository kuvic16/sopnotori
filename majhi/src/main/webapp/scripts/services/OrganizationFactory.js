app.factory('OrganizationResource', function($resource){
	var baseUrl = 'rest/organization';
	var resource = {};
    resource = $resource(baseUrl + '/:OrganizationId',
    		{OrganizationId:'@id'},
    		{
    			'queryAll'	:{method:'GET',isArray:false, params: {all:'@all', start:'@start', max:'@max', name:'@name', code:'@code', email: '@emailId' }},
    			'query'		:{method:'GET',isArray:false},
    			'update'	:{method:'PUT'}
    		}
    	);
    return resource;
});