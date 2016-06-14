app.factory('GradePointResource', function($resource) {
	var baseUrl = 'rest/grade-point';
	var resource = {};
	resource = $resource(baseUrl + '/:GradePointId', 
			{GradePointId : '@id'},
			{
				'queryAll' : {method : 'GET', isArray : false, 
					params : 
						{
							all : '@all', 
							start : '@start', 
							max : '@max', 
							letterGrade : '@letterGrade', 
							educationTypeId : '@educationTypeId' 
						}
				},
				'query' : {method : 'GET',isArray : false},
				'update' : {method : 'PUT'},
				'listByEducationType' : {method : 'GET', url : baseUrl + '/educationType/:EducationTypeId'}
			}
		);
	return resource;
});
