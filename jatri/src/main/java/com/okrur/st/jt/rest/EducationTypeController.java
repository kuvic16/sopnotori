package com.okrur.st.jt.rest;

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

import com.okrur.st.jt.rest.model.EducationTypeModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.EducationTypeService;

/**
 * @File EducationTypeController.java: EducationType rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/education-type")
public class EducationTypeController {
	@EJB
	private EducationTypeService educationTypeService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(EducationTypeModel model) {
		return educationTypeService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return educationTypeService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return educationTypeService.findById(id);
	}
	
	@GET
	@Path("/institute/{id}")
	@Produces("application/json")
	public ResponseModel findAllByInstituteId(@PathParam("id") String id) {
		return educationTypeService.findAllByInstituteId(id);
	}

	
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public ResponseModel list() {
		return educationTypeService.listAll();
	}

	@GET
	@Produces("application/json")	
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("name") String name, @QueryParam("scale") String scale) {
		return educationTypeService.listAll(startPosition, maxResult, name, scale);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, EducationTypeModel model) {
		return educationTypeService.update(id, model);
	}
}
