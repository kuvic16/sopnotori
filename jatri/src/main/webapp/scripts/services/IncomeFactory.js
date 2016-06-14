app.factory('IncomeResource', function($resource){
	var baseUrl = 'rest/income';
	var resource = {};
    resource = $resource( baseUrl + '/:IncomeId',
    		{IncomeId:'@id'},
    		{
    			'queryAll': { 
    				method: 'GET', 
    				isArray: false, 
    				params: { 
    					start: '@start',
    					max: '@max',
    					locationHierarchy: '@locationHierarchy',
    					indicatorUdvId: '@indicatorUdvId',
    					month: '@month',
    					year: '@year',
    					verified: '@verified'					     
    				}
    			},
    			'update':{
    				method:'PUT'
    			},
    			'report':{
    				method:'GET', 
    				url: baseUrl + "/report", 
    				params: { 
    					 locationHierarchy: '@locationHierarchy',
					     indicatorUdvId: '@indicatorUdvId',    																   
					     month: '@month',
					     year: '@year',
					     verified: '@verified'    																   
				     }
    			}
       		}
    );
    return resource;
});
