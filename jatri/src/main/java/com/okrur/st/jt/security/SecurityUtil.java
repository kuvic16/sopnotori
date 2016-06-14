package com.okrur.st.jt.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.okrur.st.jt.rest.model.UserModel;

/**
 * @File SecurityUtil.java
 * @author shaiful islam palash | kuvic16@gmail.com
 */

public class SecurityUtil {
	
	/**
	 * Get current logged in user model
	 * @return UserModel
	 */
	public static UserModel getCurrentLoggedUser() {
		UserModel uModel = null;
		try {
			uModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return uModel;
	}

	/**
	 * Get current logged in user id
	 * @return String
	 */
    public static String getCurrentUserId() {
        String currentUserID = "";
        try {
			UserModel uModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentUserID = uModel.getId();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return currentUserID;
    }
    
    /**
     * Get current logged in user name
     * @return String
     */
    public static String getCurrentUserName() {
        String currentUserName = "";
        try {
			UserModel uModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentUserName = uModel.getUsername();
		} catch (Throwable t) {
			t.printStackTrace();
		}
        return currentUserName;
    }
    
    

    public static boolean isAuthenticated() {
    	try {
			UserModel uModel = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(uModel != null) 
				return true;
		} catch (Throwable t) {
			t.printStackTrace();
		}
        return false;
    }
}
