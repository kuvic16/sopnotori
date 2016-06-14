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

import com.okrur.st.mj.rest.model.OrganizationModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.OrganizationService;

/**
 * @File OrganizationController.java: Organization rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/organization")
public class OrganizationController {
	@EJB
	private OrganizationService organizationService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(OrganizationModel model) {
		return organizationService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return organizationService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return organizationService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("all") boolean all, 
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@QueryParam("name") String name, 
									@QueryParam("code") String code,
									@QueryParam("email") String emailId
								) 
	{
		if(all){
			return organizationService.listAll();
		}else{
			return organizationService.listAll(startPosition, maxResult, name, code, emailId);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, OrganizationModel model) {
		return organizationService.update(id, model);
	}
}
