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

import org.apache.commons.lang3.StringUtils;

import com.okrur.st.jt.rest.model.ResponseModel;
import com.okrur.st.jt.rest.model.UdvModel;
import com.okrur.st.jt.service.UdvService;

/**
 * @File LinkedMapController.java: LinkedMap rest api
 * @author Md. Shaiful Islam Palash | kuvic16@gmail.com
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/udv")
public class UdvController {
	@EJB
	private UdvService udvService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(UdvModel model) {
		return udvService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return udvService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return udvService.findById(id);
	}
	
	@GET
	@Path("/category")
	@Produces("application/json")
	public ResponseModel categoryList() {
		return udvService.categoryList();
	}
	
	@GET
	@Path("/list/{category_names}")
	@Produces("application/json")
	public ResponseModel udvList(@PathParam("category_names") String categoryNames) {
		return udvService.udvList(categoryNames);
	}
	
	@GET
	@Path("/listByParentId")
	@Produces("application/json")
	public ResponseModel udvListByParentId(@QueryParam("categoryName") String categoryName, @QueryParam("parentId") String parentId) {
		if(StringUtils.isNotBlank(categoryName))
			return udvService.udvList(categoryName, parentId);
		else
			return udvService.udvListByParentId(parentId);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(
									@QueryParam("start") Integer startPosition, 
									@QueryParam("max") Integer maxResult, 
									@QueryParam("category") String category, 
									@QueryParam("value") String value, 
									@QueryParam("code") String code
								) 
	{
		return udvService.listAll(startPosition, maxResult, category, value, code);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, UdvModel model) {
		return udvService.update(id, model);
	}
}
