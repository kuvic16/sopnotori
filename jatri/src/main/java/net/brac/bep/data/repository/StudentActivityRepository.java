package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.StudentActivity;

/**
 * @File StudentActivityRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class StudentActivityRepository extends AbstractRepository<StudentActivity>{
	public StudentActivityRepository() {
		super(StudentActivity.class);
	}	
}