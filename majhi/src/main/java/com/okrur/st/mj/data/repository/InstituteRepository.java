package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.Institute;

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