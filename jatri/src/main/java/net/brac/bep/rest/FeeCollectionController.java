package net.brac.bep.rest;

import java.util.List;

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

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.StudentFeeModel;
import net.brac.bep.service.FeeCollectionService;

/**
 * @File StudentController.java: Student rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@Path("/fee-collection")
public class FeeCollectionController {
	@EJB
	private FeeCollectionService feeCollectionService;
	
	@GET
	@Path("/hierarchySearch")
	@Produces("application/json")
	public ResponseModel hierarchySearch(@QueryParam("init") boolean init, 
										 @QueryParam("hierarchyId") String hierarchyId, 
										 @QueryParam("instituteId") String instituteId,
										 @QueryParam("gradeId") String gradeId,
										 @QueryParam("studentId") String studentId,
										 @QueryParam("year") Integer year,
										 @QueryParam("month") Integer month,
										 @QueryParam("searchTypeId") String searchTypeId,
										 @QueryParam("searchType") String searchType) {
		return feeCollectionService.hierarchySearch(init, hierarchyId, instituteId, gradeId, studentId, year, month, searchTypeId, searchType);
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(StudentFeeModel model) {
		return feeCollectionService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return feeCollectionService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return feeCollectionService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("feeId") String feeId, @QueryParam("instituteId") String instituteId) {
		return feeCollectionService.listAll(startPosition, maxResult, feeId, instituteId);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, StudentFeeModel model) {
		return feeCollectionService.update(id, model);
	}
	
	@GET
	@Path("/listByStudent")
	@Produces("application/json")
	public ResponseModel listByStudentId(@QueryParam("studentRoll") String studentRoll, @QueryParam("studentId") String studentId, @QueryParam("instituteId") String instituteId, @QueryParam("gradeId") String gradeId, @QueryParam("year") int year, @QueryParam("month") int month ) {
		return feeCollectionService.listByStudent(studentRoll, studentId, instituteId, gradeId, year, month);
	}
	
	@GET
	@Path("/listAllByStudent")
	@Produces("application/json")
	public ResponseModel listAllByStudent(@QueryParam("studentId") String studentId, @QueryParam("year") int year){
		return feeCollectionService.listAllByStudent(studentId, year);
	}
	
	@PUT
	@Path("/updateAll")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel updateAll(List<StudentFeeModel> modelList) {
		return feeCollectionService.updateAll(modelList);
	}
}
