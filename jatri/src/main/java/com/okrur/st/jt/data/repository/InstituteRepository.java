package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Institute;

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