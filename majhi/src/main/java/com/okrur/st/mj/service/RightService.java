package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.RightModel;

public interface RightService extends AbstractService<RightModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String desc);
	public ResponseModel listAll();
}
