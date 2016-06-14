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

import net.brac.bep.rest.model.GradingSystemModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.GradingSystemService;

/**
 * @File  GradingSystemController.java:  GradingSystem rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/grading-system")
public class GradingSystemController {
	@EJB
	private  GradingSystemService gradingSystemService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(GradingSystemModel model) {
		return gradingSystemService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return gradingSystemService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return gradingSystemService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("name") String name, @QueryParam("scale") String scale) {
		return gradingSystemService.listAll(startPosition, maxResult, name, scale);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, GradingSystemModel model) {
		return gradingSystemService.update(id, model);
	}
}
