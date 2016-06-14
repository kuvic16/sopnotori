package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.StudentFee;

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