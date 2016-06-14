app.factory('GradingSystemResource', function($resource){
	var baseUrl = 'rest/grading-system';
	var resource = {};
	resource = $resource(baseUrl + '/:GradingSystemId',
			{GradingSystemId:'@id'},
			{
				'queryAll':{method:'GET',isArray:false, params: {start:'@start', max:'@max', name:'@name', scale:'@scale'}},
				'query':{method:'GET',isArray:false},
				'update':{method:'PUT'}
			}
	);
    return resource;
});