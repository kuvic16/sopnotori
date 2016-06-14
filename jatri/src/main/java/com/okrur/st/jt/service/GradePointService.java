package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.GradePointModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File GradePointService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface GradePointService  extends AbstractService<GradePointModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String letterGrade, String minMark);
	public ResponseModel findAllByEducationTypeId(String educationTypeId);
}
