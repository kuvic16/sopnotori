app.factory('RightResource', function($resource){
    var resource = $resource('rest/right/:RightId',
    		{RightId:'@id'},
    		{'queryAll':{method:'GET',isArray:false, params: {all:'@all', start:'@start', max:'@max', name:'@name', desc:'@desc'}},
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'}
    		}
    	);
    return resource;
});