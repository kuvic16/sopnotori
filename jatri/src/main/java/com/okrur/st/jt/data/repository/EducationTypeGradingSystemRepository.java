package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.EducationTypeGradingSystem;

/**
 * @File EducationTypeGradingSystemRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Stateless
public class EducationTypeGradingSystemRepository extends AbstractRepository<EducationTypeGradingSystem>{
	public EducationTypeGradingSystemRepository() {
		super(EducationTypeGradingSystem.class);
	}	
}