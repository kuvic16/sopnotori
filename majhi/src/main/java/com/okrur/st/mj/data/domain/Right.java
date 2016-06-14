package com.okrur.st.mj.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.okrur.st.mj.rest.model.RightModel;

@Entity
@XmlRootElement
@Table(name = "rights")
public class Right extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "status")
	private boolean status = true;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String getObjCode() {
		return "rght";
	}
	
	public Right(){}
	public Right setRight(RightModel model){
		this.setId(model.getId());
		this.setName(model.getName());
		this.setDescription(model.getDescription());
		return this;
	}
}
