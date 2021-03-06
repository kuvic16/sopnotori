package com.okrur.st.mj.rest;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.okrur.st.mj.rest.model.LoginModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.UserModel;
import com.okrur.st.mj.security.AppAuthProvider;
import com.okrur.st.mj.security.SecurityUtil;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File AuthController.java: auth controller
 * @author Shaiful Islam Palash | kuvic16@gmail.com
 */
@Stateless
@Path("/auth")
public class AuthController {

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel login(LoginModel loginModel) {
		ResponseModel resModel = new ResponseModel();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword());
		token.setDetails(loginModel);
		try {
			AppAuthProvider appAuthProvider = new AppAuthProvider();
			Authentication auth = appAuthProvider.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			UserModel userModel = SecurityUtil.getCurrentLoggedUser();
			resModel.setSuccess(RestUtil.SUCCESS);
			resModel.setMessage(RestUtil.SUCCESSFUL_MESSAGE);
			resModel.setModel(userModel);
			return resModel;
		}catch(BadCredentialsException be){
			resModel.setSuccess(RestUtil.ERROR);
			resModel.setMessage(RestUtil.CREDENTIAL_ERROR_MESSAGE);
		} catch (Throwable e) {
			resModel.setSuccess(RestUtil.ERROR);
			resModel.setMessage(RestUtil.AUTHENTICATION_ERROR_MESSAGE);
		}
		return resModel;
	}
}
