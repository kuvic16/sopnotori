package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.ExpenditureModel;

/**
 * @File Expenditure.java: Expenditure database entity
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate April 20, 2016
 */
@Entity
@XmlRootElement
@Table(name = "expenditure")
public class Expenditure extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "expenditure_type_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String expenditureTypeUdvId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "heads_of_account_udv_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String headsOfAccountUdvId;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_id", length = DomainConstant.ID_LENGTH)
	private String locationId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String locationHierarchy; 
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "amount")
	private double amount;
	
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "doc_name", nullable = true, length = DomainConstant.ID_LENGTH)
	private String docName;
	
	
	public String getExpenditureTypeUdvId() {
		return expenditureTypeUdvId;
	}

	public void setExpenditureTypeUdvId(String expenditureTypeUdvId) {
		this.expenditureTypeUdvId = expenditureTypeUdvId;
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

	@Override
	public String getObjCode() {
		return "Expenditure";
	}
	
	public Expenditure(){}
	public Expenditure setExpenditure(ExpenditureModel model){
		this.setId(model.getId());
		this.setExpenditureTypeUdvId(model.getExpenditureTypeUdvId());
		this.setLocationId(model.getLocationId());
		this.setLocationHierarchy(model.getLocationHierarchy());
		this.setYear(model.getYear());
		this.setAmount(model.getAmount());
		this.setDocName(model.getDocName());
		this.setMonth(model.getMonth());
		this.setHeadsOfAccountUdvId(model.getHeadsOfAccountUdvId());
		return this;
	}	
}
