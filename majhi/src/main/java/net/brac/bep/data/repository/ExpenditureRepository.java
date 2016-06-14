package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.Expenditure;

/**
 * @File ExpenditureRepository.java
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @CreationDate April 20, 2016
 */
@Stateless
public class ExpenditureRepository extends AbstractRepository<Expenditure>{
	public ExpenditureRepository() {
		super(Expenditure.class);
	}	
}