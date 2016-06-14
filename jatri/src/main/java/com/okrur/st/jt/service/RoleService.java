package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.RoleModel;

public interface RoleService extends AbstractService<RoleModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
