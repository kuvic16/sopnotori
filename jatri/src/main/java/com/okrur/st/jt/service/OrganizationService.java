package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.OrganizationModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File OrganizationService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface OrganizationService extends AbstractService<OrganizationModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String name, String code, String emailId);
	public ResponseModel listAll();
}
