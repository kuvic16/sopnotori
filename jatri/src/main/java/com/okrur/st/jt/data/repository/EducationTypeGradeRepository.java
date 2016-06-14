package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.EducationTypeGrade;

/**
 * @File EducationTypeGradeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 03, 2015
 */
@Stateless
public class EducationTypeGradeRepository extends AbstractRepository<EducationTypeGrade>{
	public EducationTypeGradeRepository() {
		super(EducationTypeGrade.class);
	}	
}