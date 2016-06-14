package net.brac.bep.data.repository;

import javax.ejb.Stateless;

import net.brac.bep.data.domain.User;

/**
 * @File UserRepository.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Nov 25, 2015
 */
@Stateless
public class UserRepository extends AbstractRepository<User>{
	public UserRepository() {
		super(User.class);
	}	
}