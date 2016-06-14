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

import com.okrur.st.mj.rest.model.IncomeModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.IncomeService;
import com.okrur.st.mj.service.UtilityService;

/**
 * @File IncomeController.java: IncomeController rest api
 * @author Md. Shaiful Islam | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate April 24, 2016
 */
@Stateless
@Path("/income")
public class IncomeController {
	@EJB
	private IncomeService incomeService;
	@EJB
	private UtilityService utilityService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(IncomeModel model) {
		return incomeService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return incomeService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return incomeService.findById(id);
	}

	
	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("start") Integer startPosition, 
								 @QueryParam("max") Integer maxResult,
								 @QueryParam("locationHierarchy") String locationHierarchy,
								 @QueryParam("indicatorUdvId") String indicatorUdvId,
								 @QueryParam("month") String month,
								 @QueryParam("year") String year,
								 @QueryParam("verified") String verified
        						 ){
		return incomeService.listAll(startPosition, maxResult, locationHierarchy, indicatorUdvId, month, year, verified);
		
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, IncomeModel model){
		return incomeService.update(id, model);
	}
	
	@GET
	@Path("/report")
	@Produces("application/json")
	public ResponseModel report(@QueryParam("locationHierarchy") String locationHierarchy,
								 @QueryParam("indicatorUdvId") String indicatorUdvId,
								 @QueryParam("month") String month,
								 @QueryParam("year") String year,
								 @QueryParam("verified") String verified){
		return incomeService.report(locationHierarchy, indicatorUdvId, month, year, verified);
	}
}
