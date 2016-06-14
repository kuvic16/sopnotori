package com.okrur.st.mj.service;

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.TransactionModel;

/**
 * @File TransactionService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public interface TransactionService extends AbstractService<TransactionModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String studentId, String amount);
	public ResponseModel listAll();
	
}
