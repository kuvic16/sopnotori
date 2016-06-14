package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Organization;

/**
 * @File OrganizationRepository.java
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class OrganizationRepository extends AbstractRepository<Organization>{
	public OrganizationRepository() {
		super(Organization.class);
	}	
}