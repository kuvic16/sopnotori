package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.EducationTypeGradingSystemModel;
import com.okrur.st.mj.rest.model.ResponseModel;

/**
 * @File EducationTypeGradingSystemService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public interface EducationTypeGradingSystemService extends AbstractService<EducationTypeGradingSystemModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String educationTypeId, String gradingSystemId);
}


