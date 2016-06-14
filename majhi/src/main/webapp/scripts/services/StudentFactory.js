app.factory('StudentResource', function($resource){
	var baseUrl = 'rest/student';
	var resource = {};
    resource = $resource(baseUrl + '/:StudentId',
    		{StudentId:'@id'},
    		{
    		    'queryAll': { method: 'GET', isArray: false, params: { start: '@start', max: '@max', studentId: '@studentId', sessionStart: '@sessionStart' } },
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'listBy' : {method : 'GET', url : baseUrl + '/listBy', isArray: false, params: { instituteId: '@instituteId', gradeId: '@gradeId'}}
    		}
    );
    return resource;
});
