package com.okrur.st.mj.rest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.Expenditure;


/**
 * @File FinancialReportModel.java: FinancialReportModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate May 10, 2016
 */
public class FinancialReportModel {
	
	@JsonIgnore
	private String id;
	private String expenditureTypeUdvId;
	private String headsOfAccountUdvId;
	private String locationId;
	private String locationHierarchy;
	private int year;
	private int month;
	private double amount;
	private String docName;

	@JsonIgnore
	private String expenditureTypeName;
	@JsonIgnore
	private String locationName;
	@JsonIgnore
	private List<String> rowList;
	
	public List<String> getRowList() {
		return rowList;
	}

	public void setRowList(List<String> rowList) {
		this.rowList = rowList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationHierarchy() {
		return locationHierarchy;
	}

	public void setLocationHierarchy(String locationHierarchy) {
		this.locationHierarchy = locationHierarchy;
	}

	public String getExpenditureTypeUdvId() {
		return expenditureTypeUdvId;
	}

	public void setExpenditureTypeUdvId(String expenditureTypeUdvId) {
		this.expenditureTypeUdvId = expenditureTypeUdvId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public String getExpenditureTypeName() {
		return expenditureTypeName;
	}

	public void setExpenditureTypeName(String expenditureTypeName) {
		this.expenditureTypeName = expenditureTypeName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public String getHeadsOfAccountUdvId() {
		return headsOfAccountUdvId;
	}

	public void setHeadsOfAccountUdvId(String headsOfAccountUdvId) {
		this.headsOfAccountUdvId = headsOfAccountUdvId;
	}

	public FinancialReportModel(){}
	
	public FinancialReportModel(Expenditure entity){
		this.setId(entity.getId());
		this.setExpenditureTypeUdvId(entity.getExpenditureTypeUdvId());
		this.setLocationId(entity.getLocationId());
		this.setLocationHierarchy(entity.getLocationHierarchy());
		this.setYear(entity.getYear());
		this.setMonth(entity.getMonth());
		this.setAmount(entity.getAmount());
		this.setDocName(entity.getDocName());
		this.setHeadsOfAccountUdvId(entity.getHeadsOfAccountUdvId());
	}
}
