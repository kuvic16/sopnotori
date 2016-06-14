package com.okrur.st.jt.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.DonorModel;

/**
 * @File Donor.java: Donor database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "donor")
public class Donor extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@Size(min = 0, max = DomainConstant.CODE_LENGTH)
	@Column(name = "code", length = DomainConstant.CODE_LENGTH)
	private String code;
	
	/*
	Donor Type (i.e. SPA/DC, outside SPA etc.)
	Select donor type from dropdown list
	Relation with Udv
	*/
	@Size(min = 0, max = DomainConstant.ID_LENGTH)
	@Column(name = "donor_type_udv_id", length = DomainConstant.ID_LENGTH)
	private String donorTypeUdvId;

	@NotNull
	@Size(min = 1, max = DomainConstant.NAME_LENGTH)
	@Column(name = "name", nullable = false, length = DomainConstant.NAME_LENGTH)
	private String name;

	@Size(min = 0, max = DomainConstant.DESCRIPTION_LENGTH)
	@Column(name = "contact_numbers", length = DomainConstant.DESCRIPTION_LENGTH)
	private String contactNumbers;
	
	@Size(min = 0, max = 150)
	@Column(name = "local_email", length = 150)
	private String localEmail;
	
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "ho_email", nullable = false, length = 150)
	private String hoEmail;
	
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "contact_person", nullable = false, length = 150)
	private String contactPerson;
	
	@Size(min = 0, max = 500)
	@Column(name = "local_office", length = 500)
	private String localOffice;
	
	@NotNull
	@Size(min = 1, max = 500)
	@Column(name = "ho_office", nullable = false, length = 500)
	private String hoOffice;
	
	@Size(min = 0, max = 500)
	@Column(name = "description", length = 500)
	private String description;
	
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

	public String getDonorTypeUdvId() {
		return donorTypeUdvId;
	}

	public void setDonorTypeUdvId(String donorTypeUdvId) {
		this.donorTypeUdvId = donorTypeUdvId;
	}

	public String getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(String contactNumbers) {
		this.contactNumbers = contactNumbers;
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

	@Override
	public String getObjCode() {
		return "donor";
	}
	
	public Donor(){}
	public Donor setDonor(DonorModel model){
		this.setId(model.getId());		
		this.setCode(model.getCode());
		this.setName(model.getName());
		this.setContactNumbers(model.getContactNumbers());
		this.setLocalEmail(model.getLocalEmail());
		this.setHoEmail(model.getHoEmail());
		this.setContactPerson(model.getContactPerson());
		this.setLocalOffice(model.getLocalOffice());
		this.setHoOffice(model.getHoOffice());
		this.setDescription(model.getDescription());		
		this.setDonorTypeUdvId(model.getDonorTypeUdvId());
		return this;
	}	
}
