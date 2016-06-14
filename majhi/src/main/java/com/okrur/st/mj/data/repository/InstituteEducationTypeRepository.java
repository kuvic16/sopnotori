package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.InstituteEducationType;

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