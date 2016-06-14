package net.brac.bep.rest.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.brac.bep.data.domain.Right;
import net.brac.bep.data.domain.Role;

public class RoleModel {
	
	@JsonIgnore
	private String id;
	
	private String name;
	
	private String description;
	
	@JsonIgnore
	private String totalRight;
	
	@JsonIgnore
	private List<RightModel> rightModelList;
	
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
	
	public String getTotalRight() {
		return totalRight;
	}
	public void setTotalRight(String totalRight) {
		this.totalRight = totalRight;
	}
	public List<RightModel> getRightModelList() {
		return rightModelList;
	}
	public void setRightModelList(List<RightModel> rightModelList) {
		this.rightModelList = rightModelList;
	}
	
	public RoleModel(){}
	public RoleModel(Role role){
		this.setId(role.getId());
		this.setName(role.getName());
		this.setDescription(role.getDescription());
	}
	
	public RoleModel(Role role, boolean withDetails){
		this.setId(role.getId());
		this.setName(role.getName());
		this.setDescription(role.getDescription());
		if(role.getRightList() != null && role.getRightList().size() > 0){
			this.setTotalRight(String.valueOf(role.getRightList().size()));
			if(withDetails){
				List<RightModel> rightModels = new ArrayList<RightModel>();
				for(Right right : role.getRightList()){
					rightModels.add(new RightModel(right));
				}
				this.setRightModelList(rightModels);
			}
		}else{
			this.setTotalRight("0");
		}
	}
}
