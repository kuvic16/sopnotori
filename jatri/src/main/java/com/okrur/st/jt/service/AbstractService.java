package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ResponseModel;

public interface AbstractService<T> {
	public ResponseModel create(T t);
	public ResponseModel update(String id,T t);
	public ResponseModel deleteById(String id);
	public ResponseModel findById(String id);
	public ResponseModel listAll(Integer startPosition, Integer maxResult);
}
