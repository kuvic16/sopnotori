package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.GradePoint;

/**
 * @File GradePointRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class GradePointRepository extends AbstractRepository<GradePoint>{
	public GradePointRepository() {
		super(GradePoint.class);
	}	
}