package net.brac.bep.service;

import net.brac.bep.rest.model.InstituteEducationTypeModel;
import net.brac.bep.rest.model.ResponseModel;

/**
 * @File InstituteEducationTypeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 04, 2016
 */
public interface InstituteEducationTypeService extends AbstractService<InstituteEducationTypeModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String instituteId, String educationTypeId);
	public ResponseModel listAll();
	public ResponseModel deleteByEducationTypeId(String institutesId);
}
