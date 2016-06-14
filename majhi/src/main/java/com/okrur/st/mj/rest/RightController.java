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
import com.okrur.st.mj.rest.model.RightModel;
import com.okrur.st.mj.service.RightService;

/**
 * @author Shaiful Islam Palash<154166; kuvic16@gmail.com>
 */
@Stateless
@Path("/right")
public class RightController {
	@EJB
	private RightService rightService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(RightModel rightModel) {
		return rightService.create(rightModel);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return rightService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return rightService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("all") boolean all, @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("name") String name, @QueryParam("desc") String desc) {
		if(all){
			return rightService.listAll();
		}else{
			return rightService.listAll(startPosition, maxResult, name, desc);
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, RightModel entity) {
		return rightService.update(id, entity);
	}
}
