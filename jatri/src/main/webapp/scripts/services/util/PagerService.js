app.service('PagerService', ['$rootScope',  function($rootScope){
	var pager = {};
	
	this.getPager = function(totalSize, pageSize, currentPage) {
		var result = Math.ceil(totalSize / pageSize);
		var max = (result == 0) ? 1 : result;

		var ctr = 0, ctrMax = 10;
		if (max < 10) {
			ctrMax = max;
		}
		if (currentPage > 6) {
			ctrMax = (currentPage - 1) + 10;
			if (ctrMax > max) {
				ctrMax = max
			}

			diff = 10 - (ctrMax - currentPage);
			ctr = currentPage - diff;
		}
		
		var pageRange = [];
		for(var ctr; ctr<ctrMax; ctr++) {
        	if(ctr >= result)
        		break;
            pageRange.push(ctr);
        }
		
		
		pager.maxPage = max;
		pager.pageRange = pageRange;		
		return pager;
	};
       
}]);