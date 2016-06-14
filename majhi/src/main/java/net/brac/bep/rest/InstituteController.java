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

import org.apache.commons.lang3.StringUtils;

import net.brac.bep.rest.model.InstituteModel;
import net.brac.bep.rest.model.ResponseModel;
import net.brac.bep.service.InstituteService;
import net.brac.bep.util.RestUtil;

/**
 * @File InstituteController.java: Institute rest api
 * @author Md. Nazmus Salahin Rocky | rocky.bgta@gmail.com
 * @CreationDate Nov 29, 2015
 */
@Stateless
@Path("/institute")
public class InstituteController {
	@EJB
	private InstituteService instituteService;

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public ResponseModel create(InstituteModel model) {
		return instituteService.create(model);
	}

	@DELETE
	@Path("/{id}")
	public ResponseModel deleteById(@PathParam("id") String id) {
		return instituteService.deleteById(id);
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public ResponseModel findById(@PathParam("id") String id) {
		return instituteService.findById(id);
	}

	@GET
	@Produces("application/json")
	public ResponseModel listAll(@QueryParam("locationHierarchy") 	String 	locationHierarchyList,
								@QueryParam("all") 					boolean all, 
								@QueryParam("start") 				Integer startPosition, 
								@QueryParam("max") 					Integer maxResult, 
								@QueryParam("name") 				String name, 
								@QueryParam("code") 				String code,
								@QueryParam("instituteTypeUdvId") 	String instituteTypeUdvId,
								@QueryParam("educationTypeId") 		String educationTypeId,
								@QueryParam("poId") 				String poId,
								@QueryParam("locationTypeUdvId") 	String locationTypeUdvId,
								@QueryParam("projectCodeUdvId") 	String projectCodeUdvId,
								@QueryParam("locationId") 			String locationId,
								@QueryParam("locationHierarchy") 	String locationHierarchy){
		ResponseModel response = new ResponseModel(RestUtil.MODULE_INSTITUTE, RestUtil.REQUEST_TYPE_LIST);
		if(!StringUtils.isEmpty(locationHierarchyList)){
			response = instituteService.listAll(startPosition,maxResult,name,code,instituteTypeUdvId,educationTypeId,poId,locationTypeUdvId,projectCodeUdvId,locationId,locationHierarchy);
		} else{
			if(all){
				response = instituteService.listAll();
			}else{
				response = instituteService.listAll(startPosition,maxResult,name,code,instituteTypeUdvId,educationTypeId,poId,locationTypeUdvId,projectCodeUdvId,locationId,locationHierarchy);					
			}					
		}
		return response;
	}
	
	@GET
	@Path("/hierarchy/{id}")
	@Produces("application/json")
	public ResponseModel getInstituteListByHierarchy(@PathParam("id") String id) {
		return instituteService.listAll(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public ResponseModel update(@PathParam("id") String id, InstituteModel model) {
		return instituteService.update(id, model);
	}
}
