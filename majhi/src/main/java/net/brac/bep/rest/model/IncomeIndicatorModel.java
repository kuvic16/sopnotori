package net.brac.bep.rest.model;

import java.util.List;

/**
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate May 26, 2016
 */
public class IncomeIndicatorModel {
	private String name;
	private String id;
	private List<IncomeDetailsModel> incomeDetailsModels;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<IncomeDetailsModel> getIncomeDetailsModels() {
		return incomeDetailsModels;
	}

	public void setIncomeDetailsModels(List<IncomeDetailsModel> incomeDetailsModels) {
		this.incomeDetailsModels = incomeDetailsModels;
	}

	public IncomeIndicatorModel(){}	
}
