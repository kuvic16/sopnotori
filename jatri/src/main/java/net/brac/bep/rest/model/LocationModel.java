package net.brac.bep.rest.model;

import java.io.Serializable;
import java.util.List;


public class LocationModel extends BaseModel implements Serializable {
	private static final long serialVersionUID = -1121660162894459680L;

	private String id;
	private String name;
	private String category;
	private String parentId;
	private String hierarchyId;
	private String hierarchyName;
	private String parentLocationId;
	private Integer hrdId;
	private Integer misCode;

	private List<LocationModel> listOfLocation;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getHierarchyId() {
		return hierarchyId;
	}

	public void setHierarchyId(String hierarchyId) {
		this.hierarchyId = hierarchyId;
	}

	public List<LocationModel> getListOfLocation() {
		return listOfLocation;
	}

	public void setListOfLocation(List<LocationModel> listOfLocation) {
		this.listOfLocation = listOfLocation;
	}

	public String getHierarchyName() {
		return hierarchyName;
	}

	public void setHierarchyName(String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	public String getParentLocationId() {
		return parentLocationId;
	}

	public void setParentLocationId(String parentLocationId) {
		this.parentLocationId = parentLocationId;
	}

	public Integer getHrdId() {
		return hrdId;
	}

	public void setHrdId(Integer hrdId) {
		this.hrdId = hrdId;
	}

	public Integer getMisCode() {
		return misCode;
	}

	public void setMisCode(Integer misCode) {
		this.misCode = misCode;
	}
}
