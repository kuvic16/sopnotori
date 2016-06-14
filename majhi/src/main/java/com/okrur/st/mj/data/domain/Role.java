package com.okrur.st.mj.data.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.mj.rest.model.RightModel;
import com.okrur.st.mj.rest.model.RoleModel;


@Entity
@XmlRootElement
@Table(name="roles")
public class Role extends AuditableEntity {
    
	private static final long serialVersionUID = -3088098637860167083L;

	@Column(name="name", nullable=false)
    private String name;
    
    @Column(name="description", nullable=false)
    private String description;
    
    @Column(name="status")
    private boolean status = true;
    
    @ManyToMany(targetEntity = Right.class,fetch = FetchType.LAZY)
    @JoinTable(name = "role_right", joinColumns =
        @JoinColumn(name = "role_id"), inverseJoinColumns =
        @JoinColumn(name = "right_id"))
    private Collection<Right> rightList;
    
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

    public Collection<Right> getRightList() {
        return rightList;
    }

    public void setRightList(Collection<Right> rightList) {
        this.rightList = rightList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

	@Override
	public String getObjCode() {
		return "role";
	}
	
	public Role(){}
	public Role setRole(RoleModel model){
		this.setId(model.getId());
		this.setName(model.getName());
		this.setDescription(model.getDescription());
		if(model.getRightModelList() != null && model.getRightModelList().size() > 0){
			List<Right> rights = new ArrayList<Right>();
			for(RightModel rightModel : model.getRightModelList()){
				rights.add(new Right().setRight(rightModel));
			}
			this.setRightList(rights);
		}
		return this;
	}
	
	public Role setRoleForEdit(Role role, RoleModel model){
		if(StringUtils.isNotBlank(model.getName())){
			role.setName(model.getName());
		}
		
		if(StringUtils.isNotBlank(model.getDescription())){
			role.setDescription(model.getDescription());
		}
		if(model.getRightModelList() != null && model.getRightModelList().size() > 0){
			List<Right> rights = new ArrayList<Right>();
			for(RightModel rightModel : model.getRightModelList()){
				rights.add(new Right().setRight(rightModel));
			}
			role.setRightList(rights);
		}
		return role;
	}
}
