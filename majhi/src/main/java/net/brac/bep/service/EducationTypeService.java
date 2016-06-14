package net.brac.bep.service;

import net.brac.bep.rest.model.EducationTypeModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File EducationTypeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface EducationTypeService extends AbstractService<EducationTypeModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String scale);
	public ResponseModel findAllByInstituteId(String instituteId);
	public ResponseModel listAll();
	public ResponseModel findByName(String name);
}


