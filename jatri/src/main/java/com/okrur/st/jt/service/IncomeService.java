package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.IncomeModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File IncomeService.java
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate April 24, 2016
 */
public interface IncomeService extends AbstractService<IncomeModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String locationHierarchy, String indicatorUdvId, String month, String year, String verified);
	public ResponseModel report(String locationHierarchy, String indicatorUdvId, String month, String year, String verified); 
}
