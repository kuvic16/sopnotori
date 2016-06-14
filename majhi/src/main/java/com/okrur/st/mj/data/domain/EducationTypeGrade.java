package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.EducationTypeGradeModel;

/**
 * @File EducationTypeGrade.java: EducationTypeGrade database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 3, 2015
 */
@Entity
@XmlRootElement
@Table(name = "education_type_grade")
public class EducationTypeGrade extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "education_type_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String educationTypeId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "grade_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String gradeId;
	
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

	@Override
	public String getObjCode() {
		return "institute_grade";
	}
	
	public EducationTypeGrade(){}
	public EducationTypeGrade setEducationTypeGrade(EducationTypeGradeModel model){
		this.setId(model.getId());
		this.setEducationTypeId(model.getEducationTypeId());
		this.setGradeId(model.getGradeId());
		return this;
	}
}
