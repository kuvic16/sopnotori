package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.okrur.st.mj.data.domain.GradingSystem;

/**
 * @File GradingSystemModel.java: Grading System Model for rest communication
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
public class GradingSystemModel {

	@JsonIgnore
	private String id;
	private String name;
	private float scale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public GradingSystemModel() {
	}

	public GradingSystemModel(GradingSystem entity) {
		this.setId(entity.getId());
		this.setName(entity.getName());
		this.setScale(entity.getScale());

	}
}
