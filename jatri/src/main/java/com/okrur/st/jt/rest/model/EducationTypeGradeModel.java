package com.okrur.st.jt.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.jt.data.domain.EducationTypeGrade;

/**
 * @File EducationTypeGradeModel.java: EducationTypeGradeModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public class EducationTypeGradeModel {
	
	@JsonIgnore
	private String id;
	private String educationTypeId;
	private String gradeId;
	
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

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public EducationTypeGradeModel(){}
	
	public EducationTypeGradeModel(EducationTypeGrade entity){
		this.setId(entity.getId());
		this.setEducationTypeId(entity.getEducationTypeId());
		this.setGradeId(entity.getGradeId());
	}
}
