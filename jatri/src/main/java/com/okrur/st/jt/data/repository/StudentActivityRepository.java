package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.StudentActivity;

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