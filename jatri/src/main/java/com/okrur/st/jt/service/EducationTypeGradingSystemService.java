package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.EducationTypeGradingSystemModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File EducationTypeGradingSystemService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public interface EducationTypeGradingSystemService extends AbstractService<EducationTypeGradingSystemModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String educationTypeId, String gradingSystemId);
}


