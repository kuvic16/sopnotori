package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.StudentFee;

/**
 * @File StudentFeeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate jan 11, 2016
 */
@Stateless
public class StudentFeeRepository extends AbstractRepository<StudentFee>{
	public StudentFeeRepository() {
		super(StudentFee.class);
	}	
}