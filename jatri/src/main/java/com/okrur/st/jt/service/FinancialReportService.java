package com.okrur.st.jt.service;

import com.okrur.st.jt.rest.model.FinancialReportModel;
import com.okrur.st.jt.rest.model.ResponseModel;

/**
 * @File ExpenditureService.java
 * @author Md. Shaiful Islam Palash | kuvic16.com
 * @CreationDate April 20, 2016
 */
public interface FinancialReportService extends AbstractService<FinancialReportModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult,
								 String expenditureTypeUdvId, String headsOfAccountUdvId,
								 String locationId,
								 String locationHierarchy,
								 Integer year, Integer month,
								 Integer totalNumberOfColumn);
	/*public ResponseModel getColumnsName(String headsOfAccountUdvId, String year);*/
	public ResponseModel getColumnsNameByHeadOfAccount(String headsOfAccountUdvId, String year);
}
