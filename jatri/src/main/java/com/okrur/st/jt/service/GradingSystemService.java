package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.GradingSystemModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File GradingSystemService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface GradingSystemService extends AbstractService<GradingSystemModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String scale);
}

