package net.brac.bep.service;

import javax.ws.rs.QueryParam;

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.TransactionHistoryModel;

/**
 * @File TransactionHistoryService.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 26, 2015
 */
public interface TransactionHistoryService extends AbstractService<TransactionHistoryModel>{
	public ResponseModel listAll(
									Integer startPosition, 
									Integer maxResult, 
									String locationHierarchy,
									String instituteId,
									String gradeId,
									String studentId,
									String collectionDate,
									String month
							    );
	public ResponseModel findTransactionDetailsById(Integer startPosition, Integer maxResult, String id);
	public ResponseModel listAll();
}
