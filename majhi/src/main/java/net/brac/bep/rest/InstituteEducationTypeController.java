package net.brac.bep.rest;

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

import net.brac.bep.rest.model.InstituteEducationTypeModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.InstituteEducationTypeService;

/**
 * @File InstituteEducationTypeController.java: InstituteEducationType rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 04, 2016
 */
@Stateless
@Path("/institute-education-type")
public class InstituteEducationTypeController {
	@EJB
	private InstituteEducationTypeService instituteEducationTypeService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(InstituteEducationTypeModel model) {
		return instituteEducationTypeService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return instituteEducationTypeService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return instituteEducationTypeService.findById(id);
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public ResponseModel list() {
		return instituteEducationTypeService.listAll();
	}

	@GET
	@Produces("application/json")	
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("instituteId") String instituteId, @QueryParam("educationTypeId") String educationTypeId) {
		return instituteEducationTypeService.listAll(startPosition, maxResult, instituteId, educationTypeId);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, InstituteEducationTypeModel model) {
		return instituteEducationTypeService.update(id, model);
	}
}
