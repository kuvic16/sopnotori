package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.InstituteEducationType;

/**
 * @File InstituteEducationTypeModel.java: InstituteEducationType Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public class InstituteEducationTypeModel {
	
	@JsonIgnore
	private String id;
	private String instituteId;
	private String educationTypeId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public InstituteEducationTypeModel(){}
	
	public InstituteEducationTypeModel(InstituteEducationType entity){
		this.setId(entity.getId());
		this.setInstituteId(entity.getInstituteId());
		this.setEducationTypeId(entity.getEducationTypeId());
	}
}
