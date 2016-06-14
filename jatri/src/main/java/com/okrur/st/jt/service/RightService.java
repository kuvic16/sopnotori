package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.RightModel;

public interface RightService extends AbstractService<RightModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
