package com.okrur.st.mj.rest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate March 29, 2016
 */
public class FeeCollectionSearchResultModel {
	@JsonIgnore
	private String field1Label;

	@JsonIgnore
	private String field2Label;
	
	@JsonIgnore
	private String field3Label;
	
	@JsonIgnore
	private String field4Label;
	
	@JsonIgnore
	private String field5Label;
	
	@JsonIgnore
	private String field6Label;
	
	@JsonIgnore
	private List<FeeCollectionModel> feeCollectionModels;
	
	@JsonIgnore
	private String month;
	
	@JsonIgnore
	private String year;
	
	
	public String getField1Label() {
		return field1Label;
	}


	public void setField1Label(String field1Label) {
		this.field1Label = field1Label;
	}


	public String getField2Label() {
		return field2Label;
	}


	public void setField2Label(String field2Label) {
		this.field2Label = field2Label;
	}


	public String getField3Label() {
		return field3Label;
	}


	public void setField3Label(String field3Label) {
		this.field3Label = field3Label;
	}


	public String getField4Label() {
		return field4Label;
	}


	public void setField4Label(String field4Label) {
		this.field4Label = field4Label;
	}


	public String getField5Label() {
		return field5Label;
	}


	public void setField5Label(String field5Label) {
		this.field5Label = field5Label;
	}


	public String getField6Label() {
		return field6Label;
	}


	public void setField6Label(String field6Label) {
		this.field6Label = field6Label;
	}


	public List<FeeCollectionModel> getFeeCollectionModels() {
		return feeCollectionModels;
	}


	public void setFeeCollectionModels(List<FeeCollectionModel> feeCollectionModels) {
		this.feeCollectionModels = feeCollectionModels;
	}
	
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


	public FeeCollectionSearchResultModel() {
	}
}
