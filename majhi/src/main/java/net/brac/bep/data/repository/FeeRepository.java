package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.Fee;

/**
 * @File FeeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class FeeRepository extends AbstractRepository<Fee>{
	public FeeRepository() {
		super(Fee.class);
	}	
}