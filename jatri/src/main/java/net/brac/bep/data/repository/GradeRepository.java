package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.Grade;

/**
 * @File GradeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class GradeRepository extends AbstractRepository<Grade>{
	public GradeRepository() {
		super(Grade.class);
	}	
}