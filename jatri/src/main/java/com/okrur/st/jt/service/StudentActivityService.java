package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentActivityModel;

/**
 * @File StudentActivityService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface StudentActivityService extends AbstractService<StudentActivityModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String instituteId, String result);
}
