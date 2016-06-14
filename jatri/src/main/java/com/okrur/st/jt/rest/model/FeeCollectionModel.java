package com.okrur.st.jt.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate March 29, 2016
 */
public class FeeCollectionModel {
	@JsonIgnore
	private String currentSearchType;

	@JsonIgnore
	private String nextSearchType;

	@JsonIgnore
	private String searchResultId;

	@JsonIgnore
	private String searchResultName;
	
	@JsonIgnore
	private Object totalPattern1;

	@JsonIgnore
	private Object totalPattern2;

	@JsonIgnore
	private double totalPattern3;

	@JsonIgnore
	private double totalPattern4;

	@JsonIgnore
	private double totalPattern5;


	public String getCurrentSearchType() {
		return currentSearchType;
	}

	public void setCurrentSearchType(String currentSearchType) {
		this.currentSearchType = currentSearchType;
	}

	public String getNextSearchType() {
		return nextSearchType;
	}

	public void setNextSearchType(String nextSearchType) {
		this.nextSearchType = nextSearchType;
	}

	public String getSearchResultId() {
		return searchResultId;
	}

	public void setSearchResultId(String searchResultId) {
		this.searchResultId = searchResultId;
	}

	public String getSearchResultName() {
		return searchResultName;
	}

	public void setSearchResultName(String searchResultName) {
		this.searchResultName = searchResultName;
	}

	public Object getTotalPattern1() {
		return totalPattern1;
	}

	public void setTotalPattern1(Object totalPattern1) {
		this.totalPattern1 = totalPattern1;
	}

	public Object getTotalPattern2() {
		return totalPattern2;
	}

	public void setTotalPattern2(Object totalPattern2) {
		this.totalPattern2 = totalPattern2;
	}

	public double getTotalPattern3() {
		return totalPattern3;
	}

	public void setTotalPattern3(double totalPattern3) {
		this.totalPattern3 = totalPattern3;
	}

	public double getTotalPattern4() {
		return totalPattern4;
	}

	public void setTotalPattern4(double totalPattern4) {
		this.totalPattern4 = totalPattern4;
	}

	public double getTotalPattern5() {
		return totalPattern5;
	}

	public void setTotalPattern5(double totalPattern5) {
		this.totalPattern5 = totalPattern5;
	}

	public FeeCollectionModel() {
	}
}
