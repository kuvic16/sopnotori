app.factory('InstituteResource', function($resource){
	var baseUrl = 'rest/institute';
	var resource = {};
    var resource = $resource(baseUrl +'/:InstituteId',
    			{InstituteId:'@id'},
    			{
    				'queryAll'	:{
    								method:'GET',isArray:false, 
    								params: 
    								{
    									 locationHierarchy: '@locationHierarchy',
	    								 all: 	'@all', 
	    								 start:	'@start', 
	    								 max:	'@max', 
	    								 name:	'@name', 
	    								 code:	'@code',
	    								 instituteTypeUdvId:	'@instituteTypeUdvId',
	    								 educationTypeId:		'@educationType',
	    								 poId:					'@poId',
	    								 locationTypeUdvId:		'@locationTypeUdvId',
	    								 projectCodeUdvId:		'@projectCodeUdvId',
	    								 locationId: 			'@locationId',
	    								 locationHierarchy: 	'@locationHierarchy'
	    									 
    								}
    							},
    							
    				'query'		:{method:'GET',isArray:false},
    				'update'	:{method:'PUT'},
    				'hierarchy' :{method:'GET', url: baseUrl + '/hierarchy/:id'},
    			}
    );
    return resource;
});
