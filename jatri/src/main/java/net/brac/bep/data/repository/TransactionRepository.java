package net.brac.bep.data.repository;

import javax.ejb.Stateless;
import net.brac.bep.data.domain.Transaction;

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