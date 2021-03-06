package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Fee;

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