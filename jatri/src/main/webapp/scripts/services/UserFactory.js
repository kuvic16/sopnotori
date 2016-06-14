app.factory('UserResource', function($resource){
	var baseUrl = 'rest/user';
	var resource = {};
    resource = $resource( baseUrl + '/:UserId',
    		{UserId:'@id'},
    		{
    			'queryAll'	:{
	    						method:'GET',isArray:false, 
	    						params: 
	    							{	
	    								category:			'@category', 
	    								all:				'@all', 
	    								start:				'@start', 
	    								max:				'@max', 
	    								username:			'@username', 
	    								email:				'@email',
	    								mobileNumber: 		'@mobileNumber',
	    								userCategoryUdvId: 	'@userCategoryUdvId',
	    								locationId: 		'@locationId',
	    								locationHierarchy:	'@locationHierarchy',
	    								instituteId:		'@instituteId'
	    								
	    							}
    							},
    			'query'		:{method:'GET',isArray:false},
    			'update'	:{method:'PUT'},
    			'updateSettings' :{method:'PUT', url: baseUrl + '/updateSettings'},
    			'findSettingsById' :{method:'GET', url: baseUrl + '/settings/:UserId', params:{UserId: '@UserId'}},
    			'loggedUser':{method:'GET', url: baseUrl + '/loggedUser'},
    			'listAllTeacher':{method:'GET', url: baseUrl + '/listAllTeacher', isArray:false, params:{	
																	category:			'@category', 
																	all:				'@all', 
																	start:				'@start', 
																	max:				'@max', 
																	username:			'@username', 
																	email:				'@email',
																	mobileNumber: 		'@mobileNumber',
																	userCategoryUdvId: 	'@userCategoryUdvId',
																	locationId: 		'@locationId',
																	locationHierarchy:	'@locationHierarchy',
																	instituteId:		'@instituteId'
																 }},
    		}
    	);
    return resource;
});

//'saveByTeacher':{method:'POST', url: baseUrl + '/saveByTeacher',isArray:false, params:{category:'@category'}} 