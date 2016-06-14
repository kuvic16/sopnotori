package com.okrur.st.jt.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.jt.data.domain.User;
import com.okrur.st.jt.util.DateUtil;

/**
 * @File UserSettingsModel.java 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jun 13, 2015
 */
public class UserSettingsModel{
	@JsonIgnore
	private String id;
	
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String dateOfBirth;
	private String gender;
	private String designationUdvId;
	private String staffGrageUdvId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDesignationUdvId() {
		return designationUdvId;
	}
	public void setDesignationUdvId(String designationUdvId) {
		this.designationUdvId = designationUdvId;
	}
	public String getStaffGrageUdvId() {
		return staffGrageUdvId;
	}
	public void setStaffGrageUdvId(String staffGrageUdvId) {
		this.staffGrageUdvId = staffGrageUdvId;
	}
	public UserSettingsModel(){}
	public UserSettingsModel(User entity){
		this.setId(entity.getId());
		this.setEmail(entity.getEmail());
		this.setPassword(entity.getPassword());
		this.setFirstName(entity.getFirstName());
		this.setMiddleName(entity.getMiddleName());
		this.setLastName(entity.getLastName());
		this.setMobileNumber(entity.getMobileNumber());
		this.setDateOfBirth(DateUtil.getDateString(entity.getDateOfBirth(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
		this.setGender(entity.getGender());
		this.setDesignationUdvId(entity.getDesignationUdvId());		
		this.setStaffGrageUdvId(entity.getStaffGrageUdvId());
	}
}
