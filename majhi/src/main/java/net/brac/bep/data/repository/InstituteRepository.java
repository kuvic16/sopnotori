package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.Institute;

/**
 * @File InstituteRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class InstituteRepository extends AbstractRepository<Institute>{
	public InstituteRepository() {
		super(Institute.class);
	}	
}