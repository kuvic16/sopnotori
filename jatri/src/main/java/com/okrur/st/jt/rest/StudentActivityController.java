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

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.StudentActivityModel;
import com.okrur.st.jt.service.StudentActivityService;

/**
 * @File StudentActivityController.java: StudentActivity rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/student-activity")
public class StudentActivityController {
	@EJB
	private StudentActivityService studentActivityService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(StudentActivityModel model) {
		return studentActivityService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return studentActivityService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return studentActivityService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("instituteId") String instituteId, @QueryParam("result") String result) {
		return studentActivityService.listAll(startPosition, maxResult, instituteId, result);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, StudentActivityModel model) {
		return studentActivityService.update(id, model);
	}
}
