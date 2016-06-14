app.factory('StudentActivityResource', 
			function($resource)
			{
			    var resource = $resource
			    ('rest/student-activity/:StudentActivityId',
			    		{StudentActivityId:'@id'},
			    		{
			    		    'queryAll': { method: 'GET', isArray: false, params: { start: '@start', max: '@max', instituteId: '@instituteId', result: '@result' } },
			    			'query':	{method:'GET',isArray:false},
			    			'update':	{method:'PUT'}
			    		}
			    );
			   return resource;
			}
		   );
