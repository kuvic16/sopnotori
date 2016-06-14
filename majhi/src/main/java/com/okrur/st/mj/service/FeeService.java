package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.FeeModel;
import com.okrur.st.mj.rest.model.ResponseModel;

/**
 * @File FeeService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface FeeService extends AbstractService<FeeModel>{
	public ResponseModel engine(FeeModel feeModel);
	public ResponseModel apply();
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String feeTypeUdvId, String feeCategoryUdvId, String instituteId, String code, String gradeId , int year, String amount, String mandatory);
	public ResponseModel listAll();
	public ResponseModel listBy(String instituteId, String gradeId, int year);
	public ResponseModel callStoredProcedure();
	//public ResponseModel updateByParms(String feeId, String feeTypeUdvId, String feeCategoryUdvId, String instituteId, String gradeId, int year, String mandatory, FeeModel t);
	public boolean isExist(String feeId, String feeTypeUdvId, String instituteId, String gradeId, int year);
}
