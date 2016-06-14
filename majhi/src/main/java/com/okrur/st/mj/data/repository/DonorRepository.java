package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.Donor;

/**
 * @File DonorRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class DonorRepository extends AbstractRepository<Donor>{
	public DonorRepository() {
		super(Donor.class);
	}	
}