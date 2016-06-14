/**
 * 
 */
package com.okrur.st.mj.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.okrur.st.mj.rest.model.RightModel;
import com.okrur.st.mj.rest.model.UserModel;

/**
 * @File UserDetailsAdapter.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 24, 2016
 */
public class UserDetailsAdapter implements UserDetails{
	private static final long serialVersionUID = -945202485902011676L;
	
	private UserModel userModel;
	private String userId;
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public UserDetailsAdapter(UserModel userModel) {
		this.userModel = userModel;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorityList = null;
		
		if(this.getUserModel() != null 
				&& this.getUserModel().getRoleModel() != null 
				&& this.getUserModel().getRoleModel().getRightModelList() != null
				&& this.getUserModel().getRoleModel().getRightModelList().size() > 0){
			
			authorityList = new ArrayList<GrantedAuthority>();
			for(RightModel rightModel : this.getUserModel().getRoleModel().getRightModelList()){
				authorityList.add(new AuthorizationAdapter(rightModel.getName()));
			}
		}
		return authorityList;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return userModel.getPassword();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return userModel.getUsername();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUserId() {
		return this.getUserModel().getId();
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
