package net.brac.bep.service;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.TransactionModel;

/**
 * @File TransactionService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
public interface TransactionService extends AbstractService<TransactionModel>{
	public ResponseModel listAll(Integer startPosition, Integer maxResult, String studentId, String amount);
	public ResponseModel listAll();
	
}
