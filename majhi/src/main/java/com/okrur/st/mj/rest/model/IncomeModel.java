package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.Income;
import com.okrur.st.mj.util.IUtil;


/**
 * @File IncomeModel.java: IncomeModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate May 24, 2016
 */
public class IncomeModel {
	
	@JsonIgnore
	private String id;
	private String locationId;
	private String locationHierarchy;
	private String totalInstitute;
	private String totalStudent;
	private String indicatorUdvId;
	private String targetAmount;
	private String depositAmount;
	private String month;
	private String year;
	private String submittedByUsername;
	private String verified;
	
	@JsonIgnore
	private String indicatorUdvName;

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

	public String getIndicatorUdvId() {
		return indicatorUdvId;
	}

	public void setIndicatorUdvId(String indicatorUdvId) {
		this.indicatorUdvId = indicatorUdvId;
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

	public String getTotalInstitute() {
		return totalInstitute;
	}

	public void setTotalInstitute(String totalInstitute) {
		this.totalInstitute = totalInstitute;
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

	public String getSubmittedByUsername() {
		return submittedByUsername;
	}

	public void setSubmittedByUsername(String submittedByUsername) {
		this.submittedByUsername = submittedByUsername;
	}
	
	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
	
	public String getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(String totalStudent) {
		this.totalStudent = totalStudent;
	}
	
	public String getIndicatorUdvName() {
		return indicatorUdvName;
	}

	public void setIndicatorUdvName(String indicatorUdvName) {
		this.indicatorUdvName = indicatorUdvName;
	}

	public IncomeModel(){}
	public IncomeModel(Income entity){
		this.setId(entity.getId());
		this.setLocationId(entity.getLocationId());
		this.setLocationHierarchy(entity.getLocationHierarchy());
		this.setTotalInstitute(IUtil.toString(entity.getTotalInstitute()));
		this.setTotalStudent(IUtil.toString(entity.getTotalStudent()));
		this.setIndicatorUdvId(entity.getIndicatorUdvId());
		this.setTargetAmount(IUtil.toString(entity.getTargetAmount()));
		this.setDepositAmount(IUtil.toString(entity.getDepositAmount()));
		this.setMonth(IUtil.toString(entity.getMonth()));
		this.setYear(IUtil.toString(entity.getYear()));
		this.setSubmittedByUsername(entity.getSubmittedByUsername());
		this.setVerified(IUtil.toString(entity.isVerified()));
	}
}
