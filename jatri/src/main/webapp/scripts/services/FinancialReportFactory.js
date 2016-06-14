app.factory('FinancialReportResource', function($resource){
	var baseUrl = 'rest/report';
	var resource = {};
    resource = $resource( baseUrl + '/:ExpenditureId',
    		{ExpenditureId:'@id'},
    		{
    			'getColumnValues': { method: 'GET', isArray: false, params:
																	{ 	start: 					'@start',
    																	max: 					'@max', 
    																	expenditureTypeUdvId: 	'@expenditureTypeUdvId',
    																	headsOfAccountUdvId: 	'@headsOfAccountUdvId',
																		locationId:             '@locationId',
    																	locationHierarchy: 		'@locationHierarchy',
    																	year: 					'@year',
    																	month: 					'@month',
    																	totalNumberOfColumn: 	'@totalNumberOfColumn'
    															     }
				},

				'getColumnsNameByHeadOfAccount': {method: 'GET',url: baseUrl + '/columnlist',  isArray: false,
																	params:
																	{
																		headsOfAccountUdvId: 	'@headsOfAccountUdvId',
																		year: 			 		'@year'
																	}
				}

    		}
    );
    return resource;
});
