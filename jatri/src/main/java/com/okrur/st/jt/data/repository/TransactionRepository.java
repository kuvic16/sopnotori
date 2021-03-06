package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Transaction;

/**
 * @File TransactionRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
public class TransactionRepository extends AbstractRepository<Transaction>{
	public TransactionRepository() {
		super(Transaction.class);
	}	
}