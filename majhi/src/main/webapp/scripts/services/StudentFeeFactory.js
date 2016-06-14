app.factory('StudentFeeResource', function($resource){
	var baseUrl = 'rest/student-fee';
	var resource = {};
    var resource = $resource(baseUrl + '/:StudentFeeId',
    		{StudentFeeId:'@id'},
    		{
    		    'queryAll': { method: 'GET', isArray: false, params: { start: '@start', max: '@max', feeId: '@feeId', instituteId: '@instituteId' } },
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'listByStudent':{method:'GET', url: baseUrl + '/listByStudent', isArray: false, params: { studentRoll:'@studentRoll', studentId:'@studentId', instituteId: '@instituteId', gradeId: '@gradeId', year:'@year', month:'@month' }},
    			'listAllByStudent':{method:'GET', url: baseUrl + '/listAllByStudent', isArray: false, params: {studentId:'@studentId', year:'@year'}}, 
    			'updateAll':{method:'PUT', url: baseUrl + '/updateAll'}
    		}
    );
    return resource;
});
