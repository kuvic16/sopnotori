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
import com.okrur.st.jt.rest.model.TransactionHistoryModel;
import com.okrur.st.jt.service.TransactionHistoryService;

/**
 * @File TransactionHistoryController.java: TransactionHistory rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Jan 11, 2016
 */
@Stateless
@Path("/transaction-history")
public class TransactionHistoryController {
	@EJB
	private TransactionHistoryService transactionHistoryService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(TransactionHistoryModel model) {
		return transactionHistoryService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return transactionHistoryService.deleteById(id);
	}

	@GET
	@Path("/details/{id}")
	@Produces("application/json")
	public ResponseModel details(
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@PathParam("id") String id
								) 
	{
		return transactionHistoryService.findTransactionDetailsById(startPosition, maxResult, id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return transactionHistoryService.findById(id);
	}
	
	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("all") 		boolean all, 
									@QueryParam("start") 	Integer startPosition, 
									@QueryParam("max")		Integer maxResult, 
									@QueryParam("locationHierarchy")	String locationHierarchy,
									@QueryParam("instituteId")			String instituteId,
									@QueryParam("gradeId") 				String gradeId,
									@QueryParam("studentId")			String studentId,
									@QueryParam("collectionDate") 		String collectionDate,
									@QueryParam("month") 				String month
								) 
	{
		if(all){
			return transactionHistoryService.listAll();
		}else{
			return transactionHistoryService.listAll(
														startPosition, 
														maxResult, 
														locationHierarchy, 
														instituteId,
														gradeId,
														studentId,
														collectionDate,
														month
													);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, TransactionHistoryModel model) {
		return transactionHistoryService.update(id, model);
	}
}
