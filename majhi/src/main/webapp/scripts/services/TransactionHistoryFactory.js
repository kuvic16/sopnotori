app.factory('TransactionHistoryResource', function($resource){
	var baseUrl = 'rest/transaction-history';
	var resource = {};
    var resource = $resource(baseUrl + '/:TransactionHistoryId',
    		{TransactionHistoryId:'@id'},
	    		{
    			
    		    'queryAll': 
    		    { 
    		    	method: 'GET', isArray: false, params: 
	    		    {
	    		    	start: 				'@start', 
	    		    	max: 				'@max', 
	    		    	locationHierarchy:	'@locationHierarchy',
						instituteId: 		'@instituteId',
						gradeId:			'@gradeId',
						studentId:			'@studentId',
						collectionDate:		'@collectionDate',
						month:				'@month'
	    		    } 
	    		},
    			'query':{method:'GET',isArray:false},
    			'update':{method:'PUT'},
    			'listByStudent':{method:'GET', url: baseUrl + '/listByStudent', isArray: false, params: {studentId:'@studentId', instituteId: '@instituteId', gradeId: '@gradeId', year:'@year' }},
    			'listByTransactionId':{method:'GET', url: baseUrl + '/details/:transactionId',  params: {start: '@start', max: '@max'}},
    			'updateAll':{method:'PUT', url: baseUrl + '/updateAll'}
    		}
    );
    return resource;
});
