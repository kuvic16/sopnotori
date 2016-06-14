/**
 * 
 */
package com.okrur.st.jt.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.NoResultException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UserModel;
import com.okrur.st.jt.service.UserService;
import com.okrur.st.jt.util.IConstant;
import com.okrur.st.jt.util.RestUtil;

/**
 * @File AppAuthProvider.java
 * @author shaiful islam palash | kuvic16@gmail.com
 * @CreationDate Jan 11, 2016
 */

public class AppAuthProvider implements AuthenticationProvider, Serializable, UserDetailsService {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private List<GrantedAuthority> authorityList;
	
	public AppAuthProvider() {
		try {
            userService = (UserService) new InitialContext().lookup("java:comp/userService");
        } catch (Throwable ex) {
        	ex.printStackTrace();
        }     
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		authorityList = new ArrayList<GrantedAuthority>();
//		if(isAuthenticated(auth.getPrincipal().toString(), auth.getCredentials().toString())){
//            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), this.getAuthorityList());
//        }
		
		UserModel obj = isUserAuthenticated(auth.getPrincipal().toString(), auth.getCredentials().toString());
		if(obj != null){
            return new UsernamePasswordAuthenticationToken(obj, "", this.getAuthorityList());
        }
        throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(arg0));
	}

	@Deprecated
	private boolean isAuthenticated(String username, String password){
		try{
			if(username.equals(IConstant.ADMIN_USER) && password.equals(IConstant.ADMIN_PASS)){
				addAuthority(IConstant.ADMIN_ROLE);
				return true;
			}else {
				UserDetails userDetails = loadUserByUsername(username);
				return userDetails.getPassword().equals(password);
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return false;
	}
	
	@Deprecated
	private HashMap<String, String> XisUserAuthenticated(String username, String password){
		HashMap<String, String> obj = null;
		try{
			if(username.equals(IConstant.ADMIN_USER) && password.equals(IConstant.ADMIN_PASS)){
				addAuthority(IConstant.ADMIN_ROLE);
				return setLoggedUserInfo(IConstant.ADMIN_USER, "");
			}else {
				UserDetailsAdapter userDetails = loadUserByUsername(username);
				if(userDetails.getPassword().equals(password)){
					return setLoggedUserInfo(userDetails.getUsername(), userDetails.getUserId());
				}
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return obj;
	}
	
	private UserModel isUserAuthenticated(String username, String password){
		UserModel userModel = null;
		try{
			if(username.equals(IConstant.ADMIN_USER) && password.equals(IConstant.ADMIN_PASS)){
				addAuthority(IConstant.ADMIN_ROLE);
				userModel = new UserModel();
				userModel.setUsername(username);
				return userModel;
			}else {
				UserDetailsAdapter userDetails = loadUserByUsername(username);
				if(userDetails.getPassword().equals(password)){
					return userDetails.getUserModel();
				}
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return userModel;
	}

	private void addAuthority(String rightName){
		this.getAuthorityList().add(new AuthorizationAdapter(rightName));
	}
	

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserDetailsAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsAdapter userDetails = null;
        try{
        	ResponseModel responseModel = userService.findByUsername(username);
        	if(responseModel.getSuccess().equalsIgnoreCase(RestUtil.SUCCESS) && responseModel.getModel() != null){
        		UserModel userModel = (UserModel)responseModel.getModel();
        		userDetails = new UserDetailsAdapter(userModel);
        		this.setAuthorityList((List<GrantedAuthority>)userDetails.getAuthorities());
        	}
        }catch(NoResultException e){
            throw new UsernameNotFoundException(username);
        }catch (Throwable e) {
			e.printStackTrace();
		}                
        return userDetails;
	}

	public List<GrantedAuthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<GrantedAuthority> authorityList) {
		this.authorityList = authorityList;
	}
	
	private HashMap<String, String> setLoggedUserInfo(String userName, String userId){
		HashMap<String, String> obj = new HashMap<>();
		obj.put(IConstant.AUTH_USERNAME, userName);
		obj.put(IConstant.AUTH_USERID, userId);
		return obj;
	}
}
