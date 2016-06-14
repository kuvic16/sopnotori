package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.Role;

@Stateless
public class RoleRepository extends AbstractRepository<Role> {

	public RoleRepository() {
		super(Role.class);
	}	
}
