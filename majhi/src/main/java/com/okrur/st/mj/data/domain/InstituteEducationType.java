package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.InstituteEducationTypeModel;

/**
 * @File InstituteEducationType.java: InstituteEducationType database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Entity
@XmlRootElement
@Table(name = "institute_education_type")
public class InstituteEducationType extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String instituteId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "education_type_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String educationTypeId;
	
	
	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public String getEducationTypeId() {
		return educationTypeId;
	}

	public void setEducationTypeId(String educationTypeId) {
		this.educationTypeId = educationTypeId;
	}

	@Override
	public String getObjCode() {
		return "institute_education_type";
	}
	
	public InstituteEducationType(){}
	public InstituteEducationType setInstituteEducationType(InstituteEducationTypeModel model){
		this.setId(model.getId());
		this.setInstituteId(model.getInstituteId());
		this.setEducationTypeId(model.getEducationTypeId());
		return this;
	}
}
