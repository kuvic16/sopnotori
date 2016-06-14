package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.GradingSystem;

/**
 * @File GradingSystemRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class GradingSystemRepository extends AbstractRepository<GradingSystem>{
	public GradingSystemRepository() {
		super(GradingSystem.class);
	}	
}