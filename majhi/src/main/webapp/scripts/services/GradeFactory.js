app.factory('GradeResource', function($resource){
	var baseUrl = 'rest/grade';
	var resource = {};
    var resource = $resource(baseUrl+'/:GradeId',
    		{GradeId:'@id'},
    		{
    			'queryAll':{method:'GET',isArray:false, params: {all: '@all', start:'@start', max:'@max', code:'@code', name:'@name'}},
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'listByEducationType' : {method : 'GET', url : baseUrl + '/educationType/:EducationTypeId'},
    			'listByInstituteId' : {method : 'GET', url : baseUrl + '/institute/:InstituteId'}
    		}
    );
    return resource;
});
