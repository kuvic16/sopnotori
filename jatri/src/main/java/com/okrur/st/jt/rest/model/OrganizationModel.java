package com.okrur.st.jt.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.jt.data.domain.Organization;

/**
 * @File OrganizationModel.java: Organization Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
public class OrganizationModel {

	@JsonIgnore
	private String id;
	private String name;
	private String code;
	@JsonIgnore
	private String yearOfEstablishment;
	@JsonIgnore
	private String registrationStatus;
	@JsonIgnore
	private String yearlyBudget;
	@JsonIgnore
	private String yearlyStaffAllocation;
	@JsonIgnore
	private String involvementStatusWithBrac;
	@JsonIgnore
	private String address;
	@JsonIgnore
	private String contactNumbers;
	@JsonIgnore
	private String organizationHead;
	@JsonIgnore
	private String numberOfGoverningBoardMembers;
	@JsonIgnore
	private String governingBoardMembersName;
	@JsonIgnore
	private String emailId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getYearOfEstablishment() {
		return yearOfEstablishment;
	}

	public void setYearOfEstablishment(String yearOfEstablishment) {
		this.yearOfEstablishment = yearOfEstablishment;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getYearlyBudget() {
		return yearlyBudget;
	}

	public void setYearlyBudget(String yearlyBudget) {
		this.yearlyBudget = yearlyBudget;
	}

	public String getYearlyStaffAllocation() {
		return yearlyStaffAllocation;
	}

	public void setYearlyStaffAllocation(String yearlyStaffAllocation) {
		this.yearlyStaffAllocation = yearlyStaffAllocation;
	}

	public String getInvolvementStatusWithBrac() {
		return involvementStatusWithBrac;
	}

	public void setInvolvementStatusWithBrac(String involvementStatusWithBrac) {
		this.involvementStatusWithBrac = involvementStatusWithBrac;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public String getOrganizationHead() {
		return organizationHead;
	}

	public void setOrganizationHead(String organizationHead) {
		this.organizationHead = organizationHead;
	}

	public String getNumberOfGoverningBoardMembers() {
		return numberOfGoverningBoardMembers;
	}

	public void setNumberOfGoverningBoardMembers(String numberOfGoverningBoardMembers) {
		this.numberOfGoverningBoardMembers = numberOfGoverningBoardMembers;
	}

	public String getGoverningBoardMembersName() {
		return governingBoardMembersName;
	}

	public void setGoverningBoardMembersName(String governingBoardMembersName) {
		this.governingBoardMembersName = governingBoardMembersName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public OrganizationModel() {}

	public OrganizationModel(Organization entity) {
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setCode(entity.getCode());
		this.setYearOfEstablishment(entity.getYearOfEstablishment());
		this.setRegistrationStatus(entity.getRegistrationStatus());
		this.setYearlyBudget(entity.getYearlyBudget());
		this.setYearlyStaffAllocation(entity.getYearlyStaffAllocation());
		this.setInvolvementStatusWithBrac(entity.getInvolvementStatusWithBrac());
		this.setAddress(entity.getAddress());
		this.setContactNumbers(entity.getContactNumbers());
		this.setOrganizationHead(entity.getOrganizationHead());
		this.setNumberOfGoverningBoardMembers(entity.getNumberOfGoverningBoardMembers());
		this.setGoverningBoardMembersName(entity.getGoverningBoardMembersName());
		this.setEmailId(entity.getEmailId());

	}

}
