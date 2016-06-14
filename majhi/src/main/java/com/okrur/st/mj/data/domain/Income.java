package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.IncomeModel;
import com.okrur.st.mj.util.IUtil;

/**
 * @File IncomeEntity.java: IncomeEntity 
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate May 24, 2016
 */
@Entity
@XmlRootElement
@Table(name = "income")
public class Income extends AuditableEntity implements Serializable{
	
	private static final long serialVersionUID = -5924144505041035322L;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "location_id", length = DomainConstant.ID_LENGTH)
	private String locationId;
	
	@Size(min = 0, max = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	@Column(name = "location_hierarchy", length = DomainConstant.LOCATION_HIERARCHY_ID_LENGTH)
	private String locationHierarchy; 
	
	@Column(name = "total_institute")
	private int totalInstitute;
	
	@Column(name = "total_student")
	private int totalStudent;
	
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "indicator_udv_id", length = DomainConstant.ID_LENGTH)
	private String indicatorUdvId;
	
	@Column(name = "target_amount")
	private Double targetAmount;
	
	@Column(name = "deposit_amount")
	private Double depositAmount;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "month")
	private int month;

	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "submitted_by_username", length = DomainConstant.ID_LENGTH)
	private String submittedByUsername;
	
	@Column(name="verified")
	private boolean verified;
	
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

	public int getTotalInstitute() {
		return totalInstitute;
	}

	public void setTotalInstitute(int totalInstitute) {
		this.totalInstitute = totalInstitute;
	}

	public String getIndicatorUdvId() {
		return indicatorUdvId;
	}

	public void setIndicatorUdvId(String indicatorUdvId) {
		this.indicatorUdvId = indicatorUdvId;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getSubmittedByUsername() {
		return submittedByUsername;
	}

	public void setSubmittedByUsername(String submittedByUsername) {
		this.submittedByUsername = submittedByUsername;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public int getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(int totalStudent) {
		this.totalStudent = totalStudent;
	}

	@Override
	public String getObjCode() {
		return "Income";
	}
	
	public Income(){}
	public Income setIncome(IncomeModel model){
		this.setId(model.getId());
		this.setLocationId(model.getLocationId());
		this.setLocationHierarchy(model.getLocationHierarchy());
		this.setTotalInstitute(IUtil.toInteger(model.getTotalInstitute()));
		this.setTotalStudent(IUtil.toInteger(model.getTotalStudent()));
		this.setIndicatorUdvId(model.getIndicatorUdvId());
		this.setTargetAmount(IUtil.toDouble(model.getTargetAmount()));
		this.setDepositAmount(IUtil.toDouble(model.getDepositAmount()));
		this.setMonth(IUtil.toInteger(model.getMonth()));
		this.setYear(IUtil.toInteger(model.getYear()));
		this.setSubmittedByUsername(model.getSubmittedByUsername());
		this.setVerified(IUtil.toBoolean(model.getVerified()));
		return this;
	}	
}
