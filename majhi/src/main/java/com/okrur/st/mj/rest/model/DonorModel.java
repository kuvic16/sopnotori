package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.Donor;


/**
 * @File Donor.java: Donor Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
public class DonorModel {
	
	@JsonIgnore
	private String id;
	private String code;
	private String name;
	private String contactNumbers;
	private String localEmail;
	private String hoEmail;
	private String contactPerson;
	private String localOffice;
	private String hoOffice;
	private String description;
	private String donorTypeUdvId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public String getDonorTypeUdvId() {
		return donorTypeUdvId;
	}

	public void setDonorTypeUdvId(String donorTypeUdvId) {
		this.donorTypeUdvId = donorTypeUdvId;
	}

	public String getLocalEmail() {
		return localEmail;
	}

	public void setLocalEmail(String localEmail) {
		this.localEmail = localEmail;
	}

	public String getHoEmail() {
		return hoEmail;
	}

	public void setHoEmail(String hoEmail) {
		this.hoEmail = hoEmail;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getLocalOffice() {
		return localOffice;
	}

	public void setLocalOffice(String localOffice) {
		this.localOffice = localOffice;
	}

	public String getHoOffice() {
		return hoOffice;
	}

	public void setHoOffice(String hoOffice) {
		this.hoOffice = hoOffice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DonorModel(){}
	
	public DonorModel(Donor entity){
		this.setId(entity.getId());
		this.setCode(entity.getCode());
		this.setName(entity.getName());
		this.setContactNumbers(entity.getContactNumbers());
		this.setLocalEmail(entity.getLocalEmail());
		this.setHoEmail(entity.getHoEmail());
		this.setContactPerson(entity.getContactPerson());
		this.setLocalOffice(entity.getLocalOffice());
		this.setHoOffice(entity.getHoOffice());
		this.setDescription(entity.getDescription());
		this.setDonorTypeUdvId(entity.getDonorTypeUdvId());
	}
}
