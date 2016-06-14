app.factory('FeeResource', function($resource){
	var baseUrl = 'rest/fee';
	var resource = {};
    var resource = $resource(baseUrl+'/:FeeId',
    		{FeeId:'@id'},
    		{
    			'queryAll':
    			{
    				method:'GET',isArray:false, 
    				params: 
    				{
    					all:				'@all', 
    					start:				'@start', 
    					max:				'@max', 
    					feeTypeUdvId:		'@feeTypeUdvId',
						feeCategoryUdvId:	'@feeCategoryUdvId',
						instituteId:		'@instituteId',
						code:				'@code',
						gradeId:			'@gradeId',
						year:				'@year',
						amount:				'@amount',
						mandatory:			'@mandatory'
    						
    				}
    			}
    			,
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'engine':{method:'POST', url: baseUrl + '/engine'},
    			'apply':{method:'POST', url: baseUrl + '/apply'}
    		}
    );
    return resource;
});