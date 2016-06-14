package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.Right;

public class RightModel {
	
	@JsonIgnore
	private String id;
	
	private String name;
	
	private String description;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public RightModel(){}
	public RightModel(Right right){
		this.setId(right.getId());
		this.setName(right.getName());
		this.setDescription(right.getDescription());
	}
}
