package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.InstituteGrade;

/**
 * @File InstituteGradeModel.java: InstituteGradeModel for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 1, 2015
 */
public class InstituteGradeModel {
	
	@JsonIgnore
	private String id;
	private String instituteId;
	private String gradeId;
	
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

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public InstituteGradeModel(){}
	
	public InstituteGradeModel(InstituteGrade entity){
		this.setId(entity.getId());
		this.setInstituteId(entity.getInstituteId());
		this.setGradeId(entity.getGradeId());
	}
}
