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

import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.rest.model.RoleModel;
import net.brac.bep.service.RoleService;

/**
 * @author Shaiful Islam Palash<154166; kuvic16@gmail.com>
 */
@Stateless
@Path("/role")
public class RoleController {
	@EJB
	private RoleService roleService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(RoleModel roleModel) {
		return roleService.create(roleModel);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return roleService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return roleService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("all") boolean all, @QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult, @QueryParam("name") String name, @QueryParam("desc") String desc) {
		if(all){
			return roleService.listAll();
		}else{
			return roleService.listAll(startPosition, maxResult, name, desc);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, RoleModel entity) {
		return roleService.update(id, entity);
	}
}
