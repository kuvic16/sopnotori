package com.okrur.st.jt.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.InstituteGradeModel;

/**
 * @File InstitueGrade.java: InstitueGrade database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 1, 2015
 */
@Entity
@XmlRootElement
@Table(name = "institute_grade")
public class InstituteGrade extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "institute_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String instituteId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "grade_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String gradeId;
	
	

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

	@Override
	public String getObjCode() {
		return "institute_grade";
	}
	
	public InstituteGrade(){}
	public InstituteGrade setInstituteGrade(InstituteGradeModel model){
		this.setId(model.getId());
		this.setInstituteId(model.getInstituteId());
		this.setGradeId(model.getGradeId());
		return this;
	}
}
