package com.okrur.st.mj.rest.model;

/**
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @CreationDate May 26, 2016
 */
public class IncomeDetailsModel {
	private String month;
	private String year;
	private String totalInstitute;
	private String targetAmount;
	private String depositAmount;
	private String percentAmount;
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getTotalInstitute() {
		return totalInstitute;
	}

	public void setTotalInstitute(String totalInstitute) {
		this.totalInstitute = totalInstitute;
	}

	public String getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(String targetAmount) {
		this.targetAmount = targetAmount;
	}

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	public String getPercentAmount() {
		return percentAmount;
	}

	public void setPercentAmount(String percentAmount) {
		this.percentAmount = percentAmount;
	}

	public IncomeDetailsModel(){}	
}
