package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.Income;

/**
 * @File IncomeRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate April 24, 2016
 */
@Stateless
public class IncomeRepository extends AbstractRepository<Income>{
	public IncomeRepository() {
		super(Income.class);
	}	
}