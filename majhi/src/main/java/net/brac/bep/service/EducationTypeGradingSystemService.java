package net.brac.bep.service;

import net.brac.bep.rest.model.EducationTypeGradingSystemModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File EducationTypeGradingSystemService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
public interface EducationTypeGradingSystemService extends AbstractService<EducationTypeGradingSystemModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String educationTypeId, String gradingSystemId);
}


