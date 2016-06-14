package com.okrur.st.jt.service;

import java.util.List;

public interface AbstractService_x<T> {
	public T create(T t);
	public T update(T t);
	public boolean deleteById(Long id);
	public boolean deleteById(String id);
	public T findById(String id);
	public T findById(Long id);
	public List<T> listAll(Integer startPosition, Integer maxResult);	
}
