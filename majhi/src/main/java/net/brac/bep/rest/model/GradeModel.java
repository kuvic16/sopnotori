package net.brac.bep.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.Grade;

/**
 * @File GradeModel.java: Grade Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
public class GradeModel {
	
	@JsonIgnore
	private String id;
	private String code;
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GradeModel(){}
	
	public GradeModel(Grade entity){
		this.setId(entity.getId());
		this.setCode(entity.getCode());
		this.setName(entity.getName());
	}
}
