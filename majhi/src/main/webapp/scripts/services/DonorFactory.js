app.factory('DonorResource', function($resource){
	var baseUrl = 'rest/donor';
	var resource = {};
	
	resource = $resource(baseUrl + '/:DonorId',
    		{DonorId:'@id'},
    		{
    			'queryAll':{method:'GET',isArray:false, params: {
    																all:	'@all', 
    																start:	'@start', 
    																max:	'@max', 
    																code:	'@code', 
    																name:	'@name',
    																hoEmail:'@hoEmail',
    							        							donorTypeUdvId:	'@donorTypeUdvId'
    															}
    												},
    			'query':{method:'GET',isArray:false	},
    			'update':{method:'PUT'}
    		}
    );
    return resource;
});
