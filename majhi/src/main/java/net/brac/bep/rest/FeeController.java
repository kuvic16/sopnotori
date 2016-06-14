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

import net.brac.bep.rest.model.FeeModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.FeeService;

/**
 * @File FeeController.java: FeeService rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/fee")
public class FeeController {
	@EJB
	private FeeService feeService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(FeeModel model) {
		return feeService.create(model);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/engine")
	public ResponseModel engine(FeeModel model) {
		return feeService.engine(model);
	}
	
	@POST
	@Produces("application/json")
	@Path("/apply")
	public ResponseModel apply() {
		return feeService.apply();
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return feeService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return feeService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("all") boolean all ,
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@QueryParam("feeTypeUdvId") String feeTypeUdvId,
									@QueryParam("feeCategoryUdvId") String feeCategoryUdvId,
        							@QueryParam("instituteId") String instituteId,
        							@QueryParam("code") String code,
        							@QueryParam("gradeId") String gradeId ,
        							@QueryParam("year") int year,
        							@QueryParam("amount") String amount,
        							@QueryParam("mandatory") String mandatory 
								){
		if(all){
			return feeService.listAll();
		}
		else{
			return feeService.listAll(startPosition, maxResult, feeTypeUdvId, feeCategoryUdvId, instituteId, code, gradeId, year, amount, mandatory);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel update(@PathParam("id") String id, FeeModel model){
		return feeService.update(id, model);
	}
}
