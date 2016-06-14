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

import com.okrur.st.jt.rest.model.GradeModel;
import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.service.GradeService;
import com.okrur.st.jt.util.Read_Excel_Files;

/**
 * @File GradeController.java: Grade rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/grade")
public class GradeController {
	@EJB
	private GradeService gradeService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(GradeModel model) {
		return gradeService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return gradeService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return gradeService.findById(id);
	}
	
	@GET
	@Path("/educationType/{id}")
	@Produces("application/json")
	public ResponseModel findAllByEducationTypeId(@PathParam("id") String id) {
		return gradeService.findAllByEducationTypeId(id);
	}
	
	@GET
	@Path("/institute/{id}")
	@Produces("application/json")
	public ResponseModel findAllByInstituteId(@PathParam("id") String id) {
		return gradeService.findAllByInstituteId(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("all") boolean all, @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("code") String code, @QueryParam("name") String name) {
		if(all){
			return gradeService.listAll();
		}else{
			return gradeService.listAll(startPosition, maxResult, code, name);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, GradeModel model) {
		return gradeService.update(id, model);
	}

}
