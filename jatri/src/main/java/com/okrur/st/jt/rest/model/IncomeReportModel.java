package com.okrur.st.jt.rest.model;

import java.util.List;

/**
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate May 26, 2016
 */
public class IncomeReportModel {
	private List<IncomeIndicatorModel> indicatorModels;
	private List<String> months;
	private String year;
	
	public List<IncomeIndicatorModel> getIndicatorModels() {
		return indicatorModels;
	}

	public void setIndicatorModels(List<IncomeIndicatorModel> indicatorModels) {
		this.indicatorModels = indicatorModels;
	}
	
	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public IncomeReportModel(){}	
}
