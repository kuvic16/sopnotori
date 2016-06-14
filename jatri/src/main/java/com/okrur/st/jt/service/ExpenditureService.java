package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.ExpenditureModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File ExpenditureService.java
 * @author Md. Shaiful Islam Palash | kuvic16.com
 * @CreationDate April 20, 2016
 */
public interface ExpenditureService extends AbstractService<ExpenditureModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String expenditureTypeUdvId, String headsOfAccountUdvId, String locationHierarchy, Integer year, Integer month);	
}
