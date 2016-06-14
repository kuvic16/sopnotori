package net.brac.bep.service;

import net.brac.bep.rest.model.GradePointModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File GradePointService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface GradePointService  extends AbstractService<GradePointModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String letterGrade, String minMark);
	public ResponseModel findAllByEducationTypeId(String educationTypeId);
}
