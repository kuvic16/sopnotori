app.factory('RoleResource', function($resource){
	var baseUrl = 'rest/role';
	var resource = {};
	resource = $resource( baseUrl + '/:RoleId',
			{RoleId:'@id'},
			{
				'queryAll':{method:'GET',isArray:false, params: {all:'@all', start:'@start', max:'@max', name:'@name', desc:'@desc'}},
				'query':{method:'GET',isArray:false},
				'update':{method:'PUT'}
			}
		);
    return resource;
});