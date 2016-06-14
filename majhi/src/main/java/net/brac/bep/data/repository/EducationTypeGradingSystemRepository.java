package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.EducationTypeGradingSystem;

/**
 * @File EducationTypeGradingSystemRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Stateless
public class EducationTypeGradingSystemRepository extends AbstractRepository<EducationTypeGradingSystem>{
	public EducationTypeGradingSystemRepository() {
		super(EducationTypeGradingSystem.class);
	}	
}