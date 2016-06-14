package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.ExpenditureModel;
import com.okrur.st.mj.rest.model.ResponseModel;

/**
 * @File ExpenditureService.java
 * @author Md. Shaiful Islam Palash | kuvic16.com
 * @CreationDate April 20, 2016
 */
public interface ExpenditureService extends AbstractService<ExpenditureModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String expenditureTypeUdvId, String headsOfAccountUdvId, String locationHierarchy, Integer year, Integer month);	
}
