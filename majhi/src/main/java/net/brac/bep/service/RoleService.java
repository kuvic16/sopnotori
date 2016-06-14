package net.brac.bep.service;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.RoleModel;

public interface RoleService extends AbstractService<RoleModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
