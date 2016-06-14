package com.okrur.st.jt.service;

import java.util.List;

import com.okrur.st.jt.rest.model.EducationTypeGradeModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File EducationTypeGradeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public interface EducationTypeGradeService extends AbstractService<EducationTypeGradeModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String educationTypeId, String gradeId);
	public ResponseModel createList(List<EducationTypeGradeModel> list);
	public ResponseModel deleteByEducationTypeId(String educationTypeId);
}


