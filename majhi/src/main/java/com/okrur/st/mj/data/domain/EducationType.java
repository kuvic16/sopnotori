package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.EducationTypeModel;

/**
 * @File EducationType.java: EducationType database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "education_type")
public class EducationType extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Size(min = 0, max = 500)
	@Column(name = "description", length = 500)
	private String description;
	
	@NotNull
	@Column(name = "scale", nullable = false)
	private float scale;
	
	@Size(min = 0, max = 100)
	@Column(name = "code", length=100)
	private String code;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getObjCode() {
		return "education_type";
	}
	
	public EducationType(){}
	public EducationType setEducationType(EducationTypeModel model){
		this.setId(model.getId());
		this.setName(model.getName());
		this.setDescription(model.getDescription());
		this.setScale(model.getScale());
		this.setCode(model.getCode());
		return this;
	}
}
