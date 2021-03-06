package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Student;

/**
 * @File StudentRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class StudentRepository extends AbstractRepository<Student>{
	public StudentRepository() {
		super(Student.class);
	}	
}