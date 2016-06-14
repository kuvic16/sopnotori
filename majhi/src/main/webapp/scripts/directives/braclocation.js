'use strict';

angular.module('emis').directive('braclocation', function() {
	var directive = {};

	directive.restrict = 'E';
	directive.templateUrl = 'views/BracLocation/detail.html';
	directive.scope = {
		location : "=name",
		row: "=row",
		required: "=required",
	}

	directive.compile = function(element, attributes) {
	}
	return directive;
});