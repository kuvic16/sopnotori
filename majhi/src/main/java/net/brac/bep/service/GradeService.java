package net.brac.bep.service;

import net.brac.bep.rest.model.GradeModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File GradeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface GradeService extends AbstractService<GradeModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String code,  String name);
	public ResponseModel listAll();
	public ResponseModel findAllByEducationTypeId(String educationTypeId);
	public ResponseModel findAllByInstituteId(String instituteId);
	public ResponseModel findByCode(String code);
}

