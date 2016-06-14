package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.InstituteEducationType;

/**
 * @File InstituteRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 04, 2016
 */
@Stateless
public class InstituteEducationTypeRepository extends AbstractRepository<InstituteEducationType>{
	public InstituteEducationTypeRepository() {
		super(InstituteEducationType.class);
	}	
}