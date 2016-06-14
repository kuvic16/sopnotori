package com.okrur.st.jt.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.jt.rest.model.GradeModel;

/**
 * @File Grade.java: Grade database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "grade")
public class Grade extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;
	
	@NotNull
	@Size(min = 1, max = 150)
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@NotNull
	@Size(min = 1, max = DomainConstant.CODE_LENGTH)
	@Column(name = "code", nullable = false, length = DomainConstant.CODE_LENGTH)
	private String code;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getObjCode() {
		return "grade";
	}
	
	public Grade(){}
	public Grade setGrade(GradeModel model){
		this.setId(model.getId());
		this.setCode(model.getCode());
		this.setName(model.getName());
		return this;
	}
}
