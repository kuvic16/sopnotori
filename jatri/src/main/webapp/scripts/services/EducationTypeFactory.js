app.factory('EducationTypeResource', function($resource){
	var baseUrl = 'rest/education-type';
	var resource = {};
    resource = $resource( baseUrl + '/:EducationTypeId',
    		{EducationTypeId:'@id'},
    		{
    			'queryAll': { method: 'GET', isArray: false, params: { all: '@all', start: '@start', max: '@max', name: '@name', scale: '@scale' } },
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'list' : {method : 'GET', url : baseUrl + '/list'},
    			'listByInstituteId' : {method : 'GET', url : baseUrl + '/institute/:InstituteId'}
    		}
    );
    return resource;
});
