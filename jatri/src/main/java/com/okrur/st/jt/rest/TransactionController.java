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
import com.okrur.st.jt.rest.model.TransactionModel;
import com.okrur.st.jt.service.TransactionService;

/**
 * @File DonorController.java: DonorService rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@Path("/transaction")
public class TransactionController {
	@EJB
	private TransactionService transactionService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(TransactionModel model) {
		return transactionService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return transactionService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return transactionService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("all") boolean all, @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("studentId") String studentId, @QueryParam("amount") String amount) {
		if(all){
			return transactionService.listAll();
		}else{
			return transactionService.listAll(startPosition, maxResult, studentId, amount);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, TransactionModel model) {
		return transactionService.update(id, model);
	}
}
