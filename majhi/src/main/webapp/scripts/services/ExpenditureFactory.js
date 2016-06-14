app.factory('ExpenditureResource', function($resource){
	var baseUrl = 'rest/expenditure';
	var resource = {};
    resource = $resource( baseUrl + '/:ExpenditureId',
    		{ExpenditureId:'@id'},
    		{
    			'queryAll': { method: 'GET', isArray: false, params: { start: '@start', max: '@max', 
    																   expenditureTypeUdvId: '@expenditureTypeUdvId',
    																   headsOfAccountUdvId: '@headsOfAccountUdvId',
    																   locationHierarchy: '@locationHierarchy',
    																   year: '@year',
    																   month: '@month',
    															     }
    						},
    			'update':{method:'PUT'},
    			'uploadFile':{method:'POST', url: baseUrl + '/uploadFile', transformRequest: angular.identity, headers: {'Content-Type': undefined} }
    		}
    );
    return resource;
});
