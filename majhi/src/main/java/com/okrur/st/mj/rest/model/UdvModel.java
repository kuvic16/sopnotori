package com.okrur.st.mj.rest.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.okrur.st.mj.data.domain.Udv;

/**
 * @File LinkedMapModel.java: linkedmap model for rest communication 
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 24, 2015
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UdvModel{
	@JsonIgnore
	private String id;
    private String parentId;
    private String category;
    private String value;
    private String altValue;
    private String description;
    private String hierarchyId;
    @JsonIgnore
    private String parentCategory;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAltValue() {
		return altValue;
	}

	public void setAltValue(String altValue) {
		this.altValue = altValue;
	}

	public String getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(String parentCategory) {
		this.parentCategory = parentCategory;
	}

	public UdvModel(){}
	public UdvModel(Udv entity){
		this.setId(entity.getId());
		this.setParentId(entity.getParentId());
		this.setParentCategory(entity.getParentCategory());
		this.setCategory(entity.getCategory());
		this.setValue(entity.getValue());
		this.setAltValue(entity.getAltValue());
		this.setDescription(entity.getDescription());
		this.setHierarchyId(entity.getHierarchyId());		
	}
	
}
