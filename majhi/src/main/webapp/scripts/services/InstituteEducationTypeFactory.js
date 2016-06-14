app.factory('InstituteEducationTypeResource', function($resource){
	var baseUrl = 'rest/institute-education-type';
	var resource = {};
    resource = $resource( baseUrl + '/:InstituteEducationTypeId',
    		{InstituteEducationTypeId:'@id'},
    		{
    			'queryAll': { method: 'GET', isArray: false, params: { all: '@all', start: '@start', max: '@max', instituteId: '@instituteId', educationTypeId: '@educationTypeId' } },
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'list' : {method : 'GET', url : baseUrl + '/list'}
    		}
    );
    return resource;
});
