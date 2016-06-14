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

import com.okrur.st.mj.rest.model.DonorModel;
import com.okrur.st.mj.rest.model.ResponseModel;
import com.okrur.st.mj.service.DonorService;

/**
 * @File DonorController.java: DonorService rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Dec 14, 2015
 */
@Stateless
@Path("/donor")
public class DonorController {
	@EJB
	private DonorService donorService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(DonorModel model) {
		return donorService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return donorService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return donorService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("all") boolean all, 
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@QueryParam("code") String code, 
									@QueryParam("name") String name,
									@QueryParam("hoEmail") String hoEmail,
									@QueryParam("donorTypeUdvId") String donorTypeUdvId
								) 
	{
		if(all){
			return donorService.listAll();
		}else{
			return donorService.listAll(startPosition, maxResult, name, code, hoEmail, donorTypeUdvId);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, DonorModel model) {
		return donorService.update(id, model);
	}
}
