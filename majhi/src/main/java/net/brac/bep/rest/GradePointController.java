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

import net.brac.bep.rest.model.GradePointModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.GradePointService;

/**
 * @File GradePointController.java: GradePointService rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/grade-point")
public class GradePointController {
	@EJB
	private GradePointService gradePointService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(GradePointModel model) {
		return gradePointService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return gradePointService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return gradePointService.findById(id);
	}
	
	@GET
	@Path("/educationType/{id}")
	@Produces("application/json")
	public ResponseModel findAllByEducationTypeId(@PathParam("id") String id) {
		return gradePointService.findAllByEducationTypeId(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll
	(
			@QueryParam("start") Integer startPosition, 
			@QueryParam("max") Integer maxResult, 
			@QueryParam("letterGrade") String letterGrade, 
			@QueryParam("educationTypeId") String educationTypeId
	) 
	{
		return gradePointService.listAll(startPosition, maxResult, letterGrade, educationTypeId);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, GradePointModel model) {
		return gradePointService.update(id, model);
	}
}
