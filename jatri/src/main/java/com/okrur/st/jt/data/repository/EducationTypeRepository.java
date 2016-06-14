package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.EducationType;

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
