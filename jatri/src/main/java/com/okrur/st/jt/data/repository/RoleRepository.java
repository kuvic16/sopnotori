package com.okrur.st.jt.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.jt.data.domain.Role;

@Stateless
public class RoleRepository extends AbstractRepository<Role> {

	public RoleRepository() {
		super(Role.class);
	}	
}
