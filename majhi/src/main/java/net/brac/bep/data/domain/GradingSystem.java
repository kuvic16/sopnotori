package net.brac.bep.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import net.brac.bep.rest.model.GradingSystemModel;

/**
 * @File GradingSystem.java: GradingSystem database entity
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 23, 2015
 */
@Entity
@XmlRootElement
@Table(name = "grading_system")
public class GradingSystem extends AuditableEntity implements Serializable{

	private static final long serialVersionUID = -5924144505041035322L;

	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "name", nullable = false, length = 10)
	private String name;
	
	@Column(name = "scale")
	private float scale;
	
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

	@Override
	public String getObjCode() {
		return "grading_system";
	}
	
	public GradingSystem(){}
	public GradingSystem setGradingSystem(GradingSystemModel model){
		this.setId(model.getId());
		this.setName(model.getName());
		this.setScale(model.getScale());
		return this;
	}
}
