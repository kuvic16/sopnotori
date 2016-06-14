package com.okrur.st.jt.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.jt.data.domain.EducationTypeGradingSystem;

/**
 * @File EducationTypeGradingSystemModel.java: EducationTypeGradingSystemModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public class EducationTypeGradingSystemModel {
	
	@JsonIgnore
	private String id;
	private String educationTypeId;
	private String gradingSystemId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEducationTypeId() {
		return educationTypeId;
	}

	public void setEducationTypeId(String educationTypeId) {
		this.educationTypeId = educationTypeId;
	}

	public String getGradingSystemId() {
		return gradingSystemId;
	}

	public void setGradingSystemId(String gradingSystemId) {
		this.gradingSystemId = gradingSystemId;
	}

	public EducationTypeGradingSystemModel(){}
	
	public EducationTypeGradingSystemModel(EducationTypeGradingSystem entity){
		this.setId(entity.getId());
		this.setEducationTypeId(entity.getEducationTypeId());
		this.setGradingSystemId(entity.getGradingSystemId());
	}
}
