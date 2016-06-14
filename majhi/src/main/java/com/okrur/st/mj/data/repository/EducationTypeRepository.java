package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.EducationType;

/**
 * @File EducationTypeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class EducationTypeRepository extends AbstractRepository<EducationType>{
	public EducationTypeRepository() {
		super(EducationType.class);
	}	
}
