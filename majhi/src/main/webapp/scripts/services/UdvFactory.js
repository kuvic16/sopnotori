app.factory('UdvResource', function($resource){
	var baseUrl = 'rest/udv';
	var resource = {};
    resource = $resource( baseUrl + '/:UdvId',
    		{UdvId:'@id'},
    		{
    			'queryAll':{method:'GET',isArray:false, params: {all:'@all', start:'@start', max:'@max', category:'@category', value:'@value', code:'@code'}},
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'category':{method:'GET', url: baseUrl + '/category'},
    			'udvList':{method:'GET', url: baseUrl + '/list/:CategoryNames'},
    			'udvListByParentId':{method:'GET', url: baseUrl + '/listByParentId', params: {categoryName:'@categoryName', parentId:'@parentId'}},
    		}
    	);
    return resource;
});