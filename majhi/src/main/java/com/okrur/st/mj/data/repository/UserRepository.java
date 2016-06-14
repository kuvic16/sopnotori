package com.okrur.st.mj.data.repository;

import javax.ejb.Stateless;

import com.okrur.st.mj.data.domain.User;

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