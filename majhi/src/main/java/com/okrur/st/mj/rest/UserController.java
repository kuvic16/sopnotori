package com.okrur.st.mj.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.UserModel;
import com.okrur.st.mj.rest.model.UserSettingsModel;
import com.okrur.st.mj.security.SecurityUtil;
import com.okrur.st.mj.service.UserService;
import com.okrur.st.mj.util.RestUtil;

/**
 * @File UserController.java: User rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/user")
public class UserController {
	@EJB
	private UserService userService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(UserModel model) {
		return userService.create(model);
	}
	
	/*@POST
	@Path("/saveByTeacher")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel createByTeacher(@QueryParam("module") String modelw, @QueryParam("teacher") String teacher) {
		UserModel model=null;
		return userTypeService.createByTeacher(model, teacher);
	}*/


	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return userService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return userService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("category") String category, 
								@QueryParam("all") boolean all,
								@QueryParam("start") Integer startPosition, 
								@QueryParam("max") Integer maxResult, 
								@QueryParam("username") String username, 
								@QueryParam("email") String email,
								@QueryParam("mobileNumber") String mobileNumber,
								@QueryParam("userCategoryUdvId") String userCategoryUdvId,
								@QueryParam("locationId") String locationId,
								@QueryParam("locationHierarchy") String locationHierarchy,
								@QueryParam("instituteId") String instituteId,
								@QueryParam("dropOut") String dropOut){
		if (all) {
			return userService.listAll();
		} else if (StringUtils.isNoneBlank(category) && StringUtils.isNoneBlank(locationHierarchy) ) {
			return userService.listAllPo(category, locationHierarchy);
		} else {
			return userService.listAll(startPosition, maxResult, username, email, mobileNumber, userCategoryUdvId, locationId, locationHierarchy,instituteId,dropOut );
		}
	}
	
	@GET
	@Path("/listAllTeacher")
	@Produces("application/json")
	public ResponseModel listAllTeacher(@QueryParam("category") String category, 
										@QueryParam("all") boolean all,
										@QueryParam("start") Integer startPosition, 
										@QueryParam("max") Integer maxResult, 
										@QueryParam("username") String username, 
										@QueryParam("email") String email,
										@QueryParam("mobileNumber") String mobileNumber,
										@QueryParam("userCategoryUdvId") String userCategoryUdvId,
										@QueryParam("locationId") String locationId,
										@QueryParam("locationHierarchy") String locationHierarchy,
										@QueryParam("instituteId") String instituteId,
										@QueryParam("dropOut") String dropOut){
		return userService.listAllTeacher(startPosition, maxResult, username, email, mobileNumber, userCategoryUdvId, locationId, locationHierarchy,instituteId, dropOut);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, UserModel model) {
		return userService.update(id, model);
	}
	
	@GET
	@Path("/loggedUser")
	@Produces("application/json")
	public ResponseModel getLoggedUser() {
		ResponseModel resModel = new ResponseModel();
		UserModel userModel = SecurityUtil.getCurrentLoggedUser();
		resModel.setSuccess(RestUtil.SUCCESS);
		resModel.setMessage(RestUtil.SUCCESSFUL_MESSAGE);
		resModel.setModel(userModel);
		return resModel;
	}
	
	@GET
	@Path("/settings/{id}")
	@Produces("application/json")
	public ResponseModel findSettingsById(@PathParam("id") String id) {
		return userService.findSettingsById(id);
	}
	
	@PUT
	@Path("/updateSettings")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel updateSettings(UserSettingsModel model) {
		return userService.updateSettings(model);
	}
}
