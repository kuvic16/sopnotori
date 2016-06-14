package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.RoleModel;

public interface RoleService extends AbstractService<RoleModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
