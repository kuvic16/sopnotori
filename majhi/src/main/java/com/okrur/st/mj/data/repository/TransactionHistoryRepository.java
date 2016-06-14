package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.TransactionHistory;

/**
 * @File TransactionHistoryRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class TransactionHistoryRepository extends AbstractRepository<TransactionHistory>{
	public TransactionHistoryRepository() {
		super(TransactionHistory.class);
	}	
}