package com.okrur.st.jt.service;

import java.util.List;

import com.okrur.st.jt.data.domain.Institute;
import com.okrur.st.jt.rest.model.InstituteModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File InstituteService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface InstituteService extends AbstractService<InstituteModel>{
	public ResponseModel listAll(
									Integer startPosition, 
									Integer maxResult, 
									String name, 
									String code,
									String instituteTypeUdvId, 
									String educationTypeId,
									String poId,
									String locationTypeUdvId,
									String projectCodeUdvId,
									String locationId,
									String locationHierarchy	
								);
	public ResponseModel deleteById(String id);
	public ResponseModel listAll();
	public ResponseModel listAll(String locationHierarchy);
	public ResponseModel findByNameAndCode(String name, String code);
	public List<Institute> findAll(String locationTypeUdvId, String locationHierarchy, String gradeId);
}
