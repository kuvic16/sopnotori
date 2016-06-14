package com.okrur.st.jt.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.OrganizationModel;

/**
 * @File Organization.java: Organization database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "organization")
public class Organization extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "name", nullable = false,length=150)
	private String name;

	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "code", nullable = false, length=150)
	private String code;

	@Size(min = 0, max = 50)
	@Column(name = "year_of_establishment", length=50)
	private String yearOfEstablishment;

	@Size(min = 0, max = 50)
	@Column(name = "registration_status", length=50)
	private String registrationStatus;

	@Size(min = 0, max = 150)
	@Column(name = "yearly_budget", length=150)
	private String yearlyBudget;

	@Size(min = 0, max = 150)
	@Column(name = "yearly_staff_allocation", length=150)
	private String yearlyStaffAllocation;

	@Size(min = 0, max = 50)
	@Column(name = "involvement_status_with_brac", length=50)
	private String involvementStatusWithBrac;

	@Size(min = 0, max = 500)
	@Column(name = "address", length=500)
	private String address;

	@Size(min = 0, max = 500)
	@Column(name = "contact_numbers", length=500)
	private String contactNumbers;

	@Size(min = 0, max = 500)
	@Column(name = "organization_head", length=500)
	private String organizationHead;

	@Size(min = 0, max = 150)
	@Column(name = "number_of_governing_board_members", length=150)
	private String numberOfGoverningBoardMembers; 

	@Size(min = 0, max = 500)
	@Column(name = "governing_board_members_name", length=150)
	private String governingBoardMembersName;

	@Size(min = 0, max = 150)
	@Column(name = "email_id", length = 150)
	private String emailId;
	
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

	@Override
	public String getObjCode() {
		return "organization";
	}
	
	public Organization(){}
	
	public Organization setOrganization(OrganizationModel model){
		this.setId(model.getId()); 
		this.setName(model.getName()) ;
		this.setCode(model.getCode()) ;
		this.setYearOfEstablishment(model.getYearOfEstablishment()) ;
		this.setRegistrationStatus(model.getRegistrationStatus()) ;
		this.setYearlyBudget(model.getYearlyBudget()) ;
		this.setYearlyStaffAllocation(model.getYearlyStaffAllocation()) ;
		this.setInvolvementStatusWithBrac(model.getInvolvementStatusWithBrac()) ;
		this.setAddress(model.getAddress() ) ;
		this.setContactNumbers(model.getContactNumbers() ) ;
		this.setOrganizationHead(model.getOrganizationHead() ) ;
		this.setNumberOfGoverningBoardMembers(model.getNumberOfGoverningBoardMembers());
		this.setGoverningBoardMembersName(model.getGoverningBoardMembersName()) ;
		this.setEmailId(model.getEmailId());
		return this;
	}
	
}
