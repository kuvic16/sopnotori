package net.brac.bep.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import net.brac.bep.rest.model.EducationTypeGradingSystemModel;

/**
 * @File EducationTypeGradingSystem.java: EducationTypeGradingSystem database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 3, 2015
 */
@Entity
@XmlRootElement
@Table(name = "education_type_grading_system")
public class EducationTypeGradingSystem extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "education_type_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String educationTypeId;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.ID_LENGTH)
	@Column(name = "grading_system_id", nullable = false, length = DomainConstant.ID_LENGTH)
	private String gradingSystemId;
	
	
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

	@Override
	public String getObjCode() {
		return "institute_grade";
	}
	
	public EducationTypeGradingSystem(){}
	public EducationTypeGradingSystem setEducationTypeGradingSystem(EducationTypeGradingSystemModel model){
		this.setId(model.getId());
		this.setEducationTypeId(model.getEducationTypeId());
		this.setGradingSystemId(model.getGradingSystemId());
		return this;
	}
}
