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

import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.rest.model.StudentModel;
import com.okrur.st.mj.service.StudentService;

/**
 * @File StudentController.java: Student rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/student")
public class StudentController {
	@EJB
	private StudentService studentService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(StudentModel model) {
		return studentService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return studentService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return studentService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@QueryParam("fullName") String fullName, 
									@QueryParam("studentId") String studentId,
									@QueryParam("gradeId") String gradeId,
									@QueryParam("sex") String sex,
									@QueryParam("waiver") String waiver,
									@QueryParam("guardianMobile") String guardianMobile,
									@QueryParam("dropout") String dropout,
									@QueryParam("nameOfTransferredSchool") String nameOfTransferredSchool,
									@QueryParam("instituteId") String instituteId,
									@QueryParam("typeOfEthnicityCommunityUdvId") String typeOfEthnicityCommunityUdvId,
									@QueryParam("typeOfCsnUdvId") String typeOfCsnUdvId,
									@QueryParam("locationTypeUdvId") String locationTypeUdvId,
									@QueryParam("locationId") String locationId,
									@QueryParam("locationHierarchy") String locationHierarchy
									
								) 
	{
		return studentService.listAll(startPosition, maxResult, fullName, studentId, gradeId, sex, waiver, guardianMobile, dropout, 
										nameOfTransferredSchool, instituteId, typeOfEthnicityCommunityUdvId, typeOfCsnUdvId, locationTypeUdvId, locationId, locationHierarchy);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, StudentModel model) {
		return studentService.update(id, model);
	}
	
	@GET
	@Path("/listBy")
	@Produces("application/json")
	public ResponseModel findAllByInstituteId(@QueryParam("instituteId") String instituteId, @QueryParam("gradeId") String gradeId) {
		return studentService.listBy(instituteId, gradeId);
	}
}
